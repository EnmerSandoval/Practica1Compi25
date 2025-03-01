package com.compi1.practice1.strcuts;

import android.os.Trace;

import com.compi1.practice1.symbol.SymbolTable;
import com.compi1.practice1.symbol.Tree;
import com.compi1.practice1.symbol.Type;

public abstract class Instruction {
    public Type type;
    public int line;
    public int column;

    public Instruction(Type type, int line, int column){
        this.type = type;
        this.column = column;
        this.line = line;
    }

    public abstract Object interpretar(Tree tree, SymbolTable tableSymbol);
}
