package com.compi1.practice1.strcuts;

import com.compi1.practice1.errors.ErrorL;
import com.compi1.practice1.symbol.Symbol;
import com.compi1.practice1.symbol.SymbolTable;
import com.compi1.practice1.symbol.Tree;
import com.compi1.practice1.symbol.Type;
import com.compi1.practice1.symbol.TypeData;
import com.compi1.practice1.symbol.ValueAssign;

public class Assign extends Instruction{
    private String id;
    private Instruction exp;

    public Assign(Type type, String id, Instruction exp, int line, int column){
        super(type, line, column);
        this.id = id;
        this.exp = exp;
    }

    public Assign(Type type, int line, int column){
        super(type, line, column);
    }

    @Override
    public Object interpretar(Tree tree, SymbolTable tableSymbol) {
        if(this.exp == null){
            assignValue();
        }

        var valueInterpreter = this.exp.interpretar(tree, tableSymbol);

        if(valueInterpreter instanceof ErrorL){
            return valueInterpreter;
        }

        if(this.exp.type.getTypeData() != this.type.getTypeData()){
            return new ErrorL("Hubo un erro al asignar", line, column, "SEMANTICO", "Hubo problema al asignar");
        }

        Symbol symbol = new Symbol(this.id, this.type, tableSymbol.getName(), valueInterpreter, line, column);
        boolean create = tableSymbol.setVariable(symbol);

        if(!create){
            tableSymbol.setVariable(symbol);
        }

        return null;

    }

    public void assignValue(){
        var typeData = this.type.getTypeData();
        switch (typeData){
            case ENTERO : {
                this.exp = new ValueAssign(new Type(TypeData.ENTERO), this.line, this.column, 0);
            }
            case DECIMAL : {
                this.exp = new ValueAssign(new Type(TypeData.DECIMAL), this.line, this.column, 0.0);
            }
            case BOOLEANO : {
                this.exp = new ValueAssign(new Type(TypeData.BOOLEANO), this.line, this.column, true);
            }
            case STRING : {
                this.exp = new ValueAssign(new Type(TypeData.STRING), this.line, this.column, "");
            }
            default : {
            }
        }
    }
}
