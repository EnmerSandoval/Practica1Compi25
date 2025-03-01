package com.compi1.practice1.lexer;

import java.text.ParseException;import java.util.ArrayList;
import java_cup.runtime.Symbol;
import com.compi1.practice1.errors;import com.compi1.practice1.parse.ParserSym;

%%

%public
%class Lexer
%unicode
%cup

%init{
    yyline = 1;
    yycolumn = 1;
%init}

//REGEX
WHITESP         = ([\s\t\r]+)
WHITESPCS       = ([\s\t\r\n]+)
NUM             = (-)?[0-9]+(\.[0-9]+)?
ID              = [a-zA-Z_][}a-zA-Z0-9_]*
IDHEADER        = [a-zA-Z_][a-zA-Z_]*(({WHITESP})([0-9]+))?
LISTORDERONE    = [1]"."
LISTORDER       = [0-9]+"."
LISTPLUS        = "+" ({WHITESP}) ({ID})
WORD            = (\"([^\"]|[a-zA-Z]|(\s)|[0-9])+\")
PARAGRAPH       = [^ \t\n].*

//HEADERS
HASH        = "#"
BOLD        = "**"
BOLDITALIC  = "***"
COMMA       = ","
DOT         = "."
//Symbols
EQUALS      = "="
PLUS        = "+"
MINUS       = "-"
MULT        = "*"
DIV         = "/"
POW         = "^"
LPAREN      = "("
RPAREN      = ")"
PRINT       = "print"
FORMAT      = "format"
PLOT        = "plot"
REPORTES    = "reportes"
ERRORES     = "errores"
OPERADORES  = "operadores"

%eofval{
    return new Symbol(ParserSym.EOF);
%eofval}

%{
    StringBuffer stringBuffer = new StringBuffer();
    ArrayList<ErrorL> errors = new ArrayList<ErrorL>();

    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }

    public ArrayList<ErrorL> getErrorsLexers(){
        return this.errors;
    }

%}

%%

{IDHEADER}       {return new Symbol(ParserSym.IDHEADER, yycolumn, yyline, yytext());}
{HASH}           {return new Symbol(ParserSym.HASH, yycolumn, yyline, yytext());}
{BOLDITALIC}     {return new Symbol(ParserSym.BOLDITALIC, yycolumn, yyline, yytext());}
{COMMA}          {return new Symbol(ParserSym.COMMA, yycolumn, yyline, yytext());}
{ID}             {return new Symbol(ParserSym.ID, yycolumn, yyline, yytext());}
{BOLD}           {return new Symbol(ParserSym.BOLD, yycolumn, yyline, yytext());}
{LISTORDERONE}   {return new Symbol(ParserSym.LISTORDERONE, yyline, yycolumn, yytext());}
{LISTPLUS}       {return new Symbol(ParserSym.LISTPLUS, yycolumn, yyline, yytext());}
{PARAGRAPH}      {return new Symbol(ParserSym.PARAGRAPH, yycolumn, yyline, yytext());}
{EQUALS}         {return new Symbol(ParserSym.EQUALS, yycolumn, yyline, yytext());}
{PLUS}           {return new Symbol(ParserSym.PLUS, yycolumn, yyline, yytext());}
{MINUS}          {return new Symbol(ParserSym.MINUS, yycolumn, yyline, yytext());}
{LISTORDER}      {return new Symbol(ParserSym.LISTORDER, yycolumn, yyline, yytext());}
{MULT}           {return new Symbol(ParserSym.MULT, yycolumn, yyline, yytext());}
{DIV}            {return new Symbol(ParserSym.DIV, yycolumn, yyline, yytext());}
{POW}            {return new Symbol(ParserSym.POW, yycolumn, yyline, yytext());}
{LPAREN}         {return new Symbol(ParserSym.LPAREN, yycolumn, yyline, yytext());}
{RPAREN}         {return new Symbol(ParserSym.RPAREN, yycolumn, yyline, yytext());}
{PRINT}          {return new Symbol(ParserSym.PRINT, yycolumn, yyline, yytext());}
{FORMAT}         {return new Symbol(ParserSym.FORMAT, yycolumn, yyline, yytext());}
{PLOT}           {return new Symbol(ParserSym.PLOT, yycolumn, yyline, yytext());}
{OPERADORES}     {return new Symbol(ParserSym.OPERADORES, yycolumn, yyline, yytext());}
{REPORTES}       {return new Symbol(ParserSym.REPORTES)}


{WHITESPCS}      {/*  */]

[^]             {/*de momento no hace nada*/}