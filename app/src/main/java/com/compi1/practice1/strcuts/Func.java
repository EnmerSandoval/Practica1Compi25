package com.compi1.practice1.strcuts;

import com.compi1.practice1.symbol.SymbolTable;
import com.compi1.practice1.symbol.Tree;
import com.compi1.practice1.symbol.Type;
import com.compi1.practice1.symbol.TypeData;

public class Func extends Instruction{

    private Instruction instruction;
    private int funcNumber;

    public Func(int line, int column, Instruction instruction, int funcNumber){
        super(new Type(TypeData.VOID), line, column);
        this.funcNumber = funcNumber;
        this.instruction = instruction;
    }

    @Override
    public Object interpretar(Tree tree, SymbolTable tableSymbol) {
        return null;
    }
}
