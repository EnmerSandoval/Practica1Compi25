package com.compi1.practice1.symbol;

import com.compi1.practice1.strcuts.Instruction;

public class ValueAssign extends Instruction {
    private Object value;

    public ValueAssign(Type type, int line, int column, Object value){
        super(type, line, column);
        this.value = value;
    }

    @Override
    public Object interpretar(Tree tree, SymbolTable tableSymbol) {
        return this.value;
    }
}
