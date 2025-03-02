package com.compi1.practice1.symbol;

import com.compi1.practice1.errors.ErrorL;
import com.compi1.practice1.strcuts.Instruction;

import java.util.LinkedList;

public class Tree {
    private LinkedList<Instruction> instructions;
    private String console;
    private SymbolTable symbolTable;
    private LinkedList<ErrorL> errors;
    private LinkedList<Symbol> symbols;

    public Tree(LinkedList<Instruction> instructions){
        this.instructions = instructions;
        this.console = "";
        this.symbolTable = new SymbolTable();
        this.errors = new LinkedList<>();
        this.symbols = new LinkedList<>();
    }

    public LinkedList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public LinkedList<ErrorL> getErrors() {
        return errors;
    }

    public void setErrors(LinkedList<ErrorL> errors) {
        this.errors = errors;
    }

    public LinkedList<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(LinkedList<Symbol> symbols) {
        this.symbols = symbols;
    }

    public void addSymbols(LinkedList<Symbol> newSymbol){
        this.symbols.addAll(newSymbol);
    }

    public void print(String valueConsole){
        this.console += valueConsole + "\n";
    }

    public void addStruct(Instruction instruction){
        this.instructions.add(instruction);
    }


}
