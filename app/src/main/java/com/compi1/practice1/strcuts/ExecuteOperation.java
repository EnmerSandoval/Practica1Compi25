package com.compi1.practice1.strcuts;

import com.compi1.practice1.errors.ErrorL;
import com.compi1.practice1.symbol.Operators;
import com.compi1.practice1.symbol.SymbolTable;
import com.compi1.practice1.symbol.Tree;
import com.compi1.practice1.symbol.Type;
import com.compi1.practice1.symbol.TypeData;

import java.util.ArrayList;

public class ExecuteOperation extends Instruction{
    private Instruction op1;
    private Instruction op2;
    private Operators operators;
    private Instruction unique;
    private ArrayList<Occurence> occurences;

    public ExecuteOperation(int line, int column, Operators operators, Instruction unique){
        super(new Type(TypeData.ENTERO), line, column);
        this.operators = operators;
        this.unique = unique;
        occurences = new ArrayList<>();
    }

    public ExecuteOperation(int line, int column, Instruction op1, Instruction op2, Operators operators){
        super(new Type(TypeData.ENTERO), line, column);
        this.op1 = op1;
        this.op2 = op2;
        this.operators = operators;
        occurences = new ArrayList<>();
    }

    @Override
    public Object interpretar(Tree tree, SymbolTable tableSymbol) {
        Object izquierda = null, derecha = null, only = null;
        if(this.unique != null){
            return this.unique.interpretar(tree, tableSymbol);
        } else {
            izquierda = this.op1.interpretar(tree, tableSymbol);
            if(izquierda instanceof ErrorL){
                return izquierda;
            }
            derecha = this.op2.interpretar(tree, tableSymbol);
            if(derecha instanceof ErrorL){
                return derecha;
            }
        }
        switch (operators) {
            case PLUS:
                return plusOp(izquierda, derecha);
            case MINUS:
                return minusOp(izquierda, derecha);
            case MULT:
                return multOp(izquierda, derecha);
            case DIV:
                return divOp(izquierda, derecha);
            case POW:
                return powOp(izquierda, derecha);
            default:
                return new ErrorL("Operador no reconocido", line, column, "SEMANTICO", "Operador inválido");
        }
    }

    private Object plusOp(Object op1, Object op2) {
        return calcularOperacion(op1, op2, Operators.PLUS);
    }

    private Object minusOp(Object op1, Object op2) {
        return calcularOperacion(op1, op2, Operators.MINUS);
    }

    private Object multOp(Object op1, Object op2) {
        return calcularOperacion(op1, op2, Operators.MULT);
    }

    private Object divOp(Object op1, Object op2) {
        if ((double) op2 == 0) {
            return new ErrorL("División por cero", line, column, "SEMANTICO", "No se puede dividir entre 0");
        }
        return calcularOperacion(op1, op2, Operators.DIV);
    }

    private Object powOp(Object op1, Object op2) {
        return Math.pow((double) op1, (double) op2);
    }

    private Object calcularOperacion(Object op1, Object op2, Operators operador) {
        if (op1 instanceof Integer && op2 instanceof Integer) {
            int a = (int) op1;
            int b = (int) op2;
            return switch (operador) {
                case PLUS -> a + b;
                case MINUS -> a - b;
                case MULT -> a * b;
                case DIV -> (b == 0) ? new ErrorL("División por cero", line, column, "SEMANTICO", "No se puede dividir entre 0") : a / b;
                default -> new ErrorL("Operador inválido", line, column, "SEMANTICO", "Operador no reconocido");
            };
        }
        if (op1 instanceof Double || op2 instanceof Double) {
            double a = ((Number) op1).doubleValue();
            double b = ((Number) op2).doubleValue();
            return switch (operador) {
                case PLUS -> a + b;
                case MINUS -> a - b;
                case MULT -> a * b;
                case DIV -> (b == 0) ? new ErrorL("División por cero", line, column, "SEMANTICO", "No se puede dividir entre 0") : a / b;
                default -> new ErrorL("Operador inválido", line, column, "SEMANTICO", "Operador no reconocido");
            };
        }
        return new ErrorL("Tipos incompatibles en operación", line, column, "SEMANTICO", "No se pueden operar estos tipos de datos");
    }

    public ArrayList<Occurence> getOccurences() {
        return occurences;
    }
}

