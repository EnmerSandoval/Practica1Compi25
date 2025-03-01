package com.compi1.practice1.symbol;

public class Symbol {
    private String id;
    private Type type;
    private String entorno;
    Object valor;
    private int line;
    private int column;

    public Symbol(Type type, String id){
        this.type = type;
        this.id = id;
    }

    public Symbol(String id, Type type, String entorno, Object valor, int line, int column){
        this.type = type;
        this.id = id;
        this.entorno = entorno;
        this.valor = valor;
        this.line = line;
        this.column = column;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
