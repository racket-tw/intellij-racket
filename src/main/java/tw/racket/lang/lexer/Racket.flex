package tw.racket.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import tw.racket.lang.psi.RacketTypes;

%%

%class RacketLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}
CRLF=\R
WHITE_SPACE=[\ \n\t\f]
FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
SEPARATOR=[:=]
KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "
%state WAITING_VALUE
%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return RacketTypes.COMMENT; }
<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return RacketTypes.KEY; }
<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return RacketTypes.SEPARATOR; }
<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return RacketTypes.VALUE; }
({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
[^]                                                         { return TokenType.BAD_CHARACTER; }