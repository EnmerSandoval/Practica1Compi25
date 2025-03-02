package  com.compi1.practice1.lexer;

import java.text.ParseException;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import com.compi1.practice1.parse.*;
import com.compi1.practice1.errors.ErrorL;

%%

%public
%class LexerText
%unicode
%cup

%init{
    yyline = 1;
    yycolumn = 1;
%init}

WHITESP         = ([\s\t\r]+)
WHITESPCS       = ([\s\t\r\n]+)
ID              = [a-zA-Z_][}a-zA-Z0-9_]*

IDHEADER        = [a-zA-Z_][a-zA-Z_]*(({WHITESP})([0-9]+))?
LISTORDERONE    = [1]"."
LISTORDEN       = [0-9]+"."
LISTPLUS        = "+" ({WHITESP}) ({ID})
WORD            = (\"([^\"]|[a-zA-Z]|(\s)|[0-9])+\")
PARAGRAPH       = [^ \t\n].*

HASH        = "#"
MULT        = "*"
BOLD        = "**"
BOLDITALIC  = "***"
LPAREN      = "("
RPAREN      = ")"
PRINT       = "print"

%eofval{
    return new Symbol(ParserCodigoSym.EOF);
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

{LPAREN}         {return new Symbol(ParserCodigoSym.LPAREN, yycolumn, yyline, yytext());}
{RPAREN}         {return new Symbol(ParserCodigoSym.RPAREN, yycolumn, yyline, yytext());}
{PRINT}          {return new Symbol(ParserCodigoSym.PRINT, yycolumn, yyline, yytext());}
{LISTORDERONE}   {return new Symbol(ParserCodigoSym.LISTORDERONE, yyline, yycolumn, yytext());}
{LISTORDEN}      {return new Symbol(ParserCodigoSym.LISTORDEN, yycolumn, yyline, yytext());}
{HASH}           {return new Symbol(ParserCodigoSym.HASH, yycolumn, yyline, yytext());}
{BOLDITALIC}     {return new Symbol(ParserCodigoSym.BOLDITALIC, yycolumn, yyline, yytext());}
{MULT}           {return new Symbol(ParserCodigoSym.MULT, yycolumn, yyline, yytext());}
{BOLD}           {return new Symbol(ParserCodigoSym.BOLD, yycolumn, yyline, yytext());}
{LISTPLUS}       {return new Symbol(ParserCodigoSym.LISTPLUS, yycolumn, yyline, yytext());}
{ID}             {return new Symbol(ParserCodigoSym.ID, yycolumn, yyline, yytext());}
{IDHEADER}       {return new Symbol(ParserCodigoSym.IDHEADER, yycolumn, yyline, yytext());}
{PARAGRAPH}      {return new Symbol(ParserCodigoSym.PARAGRAPH, yycolumn, yyline, yytext());}
{WORD}           {return new Symbol(ParserCodigoSym.WORD, yycolumn, yyline, yytext());}

{WHITESPCS}      {/*  */}
[^]              {errors.add(new ErrorL(yytext(), yyline, yycolumn, "LEXICO", "CARACTER NO RECONOCIDO"));}