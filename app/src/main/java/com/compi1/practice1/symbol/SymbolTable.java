package com.compi1.practice1.symbol;

import java.util.HashMap;
import java.util.LinkedList;

public class SymbolTable {
    private SymbolTable tablePrevious;
    private HashMap<String, Symbol> currentTable;
    private String name;

    public SymbolTable(){
        this.currentTable = new HashMap<>();
        this.name = "";
    }

    public SymbolTable(SymbolTable tablePrevious){
        this.tablePrevious = tablePrevious;
        this.currentTable = new HashMap<>();
        this.name = "";
    }

    public SymbolTable getTablePrevious() {
        return tablePrevious;
    }

    public void setTablePrevious(SymbolTable tablePrevious) {
        this.tablePrevious = tablePrevious;
    }

    public HashMap<String, Symbol> getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(HashMap<String, Symbol> currentTable) {
        this.currentTable = currentTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean setVariable(Symbol symbol){
        Symbol search = (Symbol) this.currentTable.get(symbol.getId().toLowerCase());

        if(search == null){
            this.currentTable.put(symbol.getId().toLowerCase(), symbol);
            return true;
        }
        return false;
    }

    public Symbol getVariable(String id){
        for (SymbolTable i = this; i != null; i = i.getTablePrevious()){
            Symbol search = (Symbol) i.currentTable.get(id.toLowerCase());

            if(search != null){
                return search;
            }
        }
        return null;
    }

    public LinkedList<Symbol> getSymbols(){
        LinkedList<Symbol> symbolList = new LinkedList<>();
        for(Symbol symbol : currentTable.values()){
            symbolList.add(symbol);
        }
        return symbolList;
    }
}
