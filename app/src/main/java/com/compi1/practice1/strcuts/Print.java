package com.compi1.practice1.strcuts;

import com.compi1.practice1.errors.ErrorL;
import com.compi1.practice1.symbol.SymbolTable;
import com.compi1.practice1.symbol.Tree;
import com.compi1.practice1.symbol.Type;
import com.compi1.practice1.symbol.TypeData;

public class Print extends Instruction{

    private Instruction instruction;

    public Print(int line, int column, Instruction instruction){
        super(new Type(TypeData.VOID), line, column);
        this.instruction = instruction;
    }

    @Override
    public Object interpretar(Tree tree, SymbolTable tableSymbol) {
        var result = this.instruction.interpretar(tree, tableSymbol);
        if(result instanceof ErrorL){
            return result;
        }
        tree.print(result.toString());
        return null;
    }
}
