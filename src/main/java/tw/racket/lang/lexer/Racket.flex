package tw.racket.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import tw.racket.lang.psi.RacketTypes.*;

%%

%class RacketLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

EOL = \n | \r | \r\n
WS_NL = [\ \t\f]
WS_CHAR = {WS_NL} | {EOL}
WS = {WS_CHAR}+
COMMENT=(";")[^\r\n]*

DIGIT=[0-9]
SPECIAL_IN_ID = "!" | "$" | "%" | "&" | "*" | "/" | ":"
                | "<" | "=" | ">" | "?" | "^" | "_" | "~"
ALPHA=[a-zA-Z]
LEGAL_IN_ID = {SPECIAL_IN_ID} | {DIGIT} | [+\-.@]
IDENTIFIER=({ALPHA}|{SPECIAL_IN_ID}) {LEGAL_IN_ID}* | "+" | "-"

// for example: #\c
CHAR_LITERAL="#\\" .
STRING_LITERAL = \" ([^\\\"] | \\[^])* \"

NUMBER_LITERAL = {NUMBER_10}
               | {NUMBER_16}
               | {NUMBER_8}
               | {NUMBER_2}

SIGN = ("+" | "-")?
EXPONENT_MARKER = [esfdlESFDL]
SUFFIX = ({EXPONENT_MARKER} {SIGN} {DIGIT_10}+)?

NUMBER_10 = {PREFIX_10} {COMPLEX_10}
COMPLEX_10 = {REAL_10}
           | {REAL_10} "@" {REAL_10}
           | {REAL_10} "+" {UREAL_10} "i"
           | {REAL_10} "-" {UREAL_10} "i"
           | {REAL_10} "+i" | {REAL_10} "-i"
           | "+" {UREAL_10} "i"
           | "-" {UREAL_10} "i"
           | "+i"
           | "-i"
REAL_10 = {SIGN} {UREAL_10}
UREAL_10 = {UINTEGER_10}
         | {UINTEGER_10} "/" {UINTEGER_10}
         | {DECIMAL_10}
DIGIT_10 = [0-9]
DECIMAL_10 = {UINTEGER_10} {SUFFIX}
           | "." {DIGIT_10}+ "#"* {SUFFIX}
           | {DIGIT_10}+ "." {DIGIT_10}* "#"* {SUFFIX}
           | {DIGIT_10}+ "#"+ "." "#"* {SUFFIX}
UINTEGER_10 = {DIGIT_10}+ "#"*
EXACTNESS = (#"i" | #"e")?
PREFIX_10 = {RADIX_10} {EXACTNESS}
          | {EXACTNESS} {RADIX_10}
RADIX_10 = "#d"?

NUMBER_16 = {PREFIX_16} {COMPLEX_16}
COMPLEX_16 = {REAL_16}
           | {REAL_16} "@" {REAL_16}
           | {REAL_16} "+" {UREAL_16} "i"
           | {REAL_16} "-" {UREAL_16} "i"
           | {REAL_16} "+i" | {REAL_16} "-i"
           | "+" {UREAL_16} "i"
           | "-" {UREAL_16} "i"
           | "+i"
           | "-i"
REAL_16 = {SIGN} {UREAL_16}
UREAL_16 = {UINTEGER_16}
         | {UINTEGER_16} "/" {UINTEGER_16}
DIGIT_16 = [0-9a-fA-F]
UINTEGER_16 = {DIGIT_16}+ "#"*
PREFIX_16 = {RADIX_16} {EXACTNESS}
          | {EXACTNESS} {RADIX_16}
RADIX_16 = "#x"

NUMBER_8 = {PREFIX_8} {COMPLEX_8}
COMPLEX_8 = {REAL_8}
           | {REAL_8} "@" {REAL_8}
           | {REAL_8} "+" {UREAL_8} "i"
           | {REAL_8} "-" {UREAL_8} "i"
           | {REAL_8} "+i" | {REAL_8} "-i"
           | "+" {UREAL_8} "i"
           | "-" {UREAL_8} "i"
           | "+i"
           | "-i"
REAL_8 = {SIGN} {UREAL_8}
UREAL_8 = {UINTEGER_8}
         | {UINTEGER_8} "/" {UINTEGER_8}
DIGIT_8 = [0-7]
UINTEGER_8 = {DIGIT_8}+ "#"*
PREFIX_8 = {RADIX_8} {EXACTNESS}
          | {EXACTNESS} {RADIX_8}
RADIX_8 = "#o"

NUMBER_2 = {PREFIX_2} {COMPLEX_2}
COMPLEX_2 = {REAL_2}
           | {REAL_2} "@" {REAL_2}
           | {REAL_2} "+" {UREAL_2} "i"
           | {REAL_2} "-" {UREAL_2} "i"
           | {REAL_2} "+i" | {REAL_2} "-i"
           | "+" {UREAL_2} "i"
           | "-" {UREAL_2} "i"
           | "+i"
           | "-i"
REAL_2 = {SIGN} {UREAL_2}
UREAL_2 = {UINTEGER_2}
         | {UINTEGER_2} "/" {UINTEGER_2}
DIGIT_2 = [01]
UINTEGER_2 = {DIGIT_2}+ "#"*
PREFIX_2 = {RADIX_2} {EXACTNESS}
          | {EXACTNESS} {RADIX_2}
RADIX_2 = "#b"

ABBREVIATION_PREFIX = "`" | "," | ",@"

TRUE = "#" [tT]
FALSE = "#" [fF]

%%

<YYINITIAL> {COMMENT} { yybegin(YYINITIAL); return COMMENT; }
<YYINITIAL> "#lang" {WS}+ {IDENTIFIER} { return LANG; }
<YYINITIAL> {
    "else"              { return ELSE; }
    "=>"                { return ARROW; }
    "define"            { return DEFINE; }
    "unquote"           { return UNQUOTE; }
    "unquote-splicing"  { return UNQUOTE_SPLICING; }
    "quote"             { return QUOTE; }
    "lambda"            { return LAMBDA; }
    "if"                { return IF; }
    "set!"              { return SET; }
    "begin"             { return BEGIN; }
    "cond"              { return COND; }
    "and"               { return AND; }
    "or"                { return OR; }
    "case"              { return CASE; }
    "let"               { return LET; }
    "let*"              { return LET_STAR; }
    "letrec"            { return LET_REC; }
    "do"                { return DO; }
    "delay"             { return DELAY; }
    "quasiquote"        { return QUASIQUOTE; }
    {TRUE}              { return TRUE; }
    {FALSE}             { return FALSE; }
    "#("                { return HASH_LPAREN; }
    "#["                { return HASH_LBRACE; }
    "#{"                { return HASH_LBRACK; }
    "("                 { return LPAREN; }
    ")"                 { return RPAREN; }
    "["                 { return LBRACE; }
    "]"                 { return RBRACE; }
    "{"                 { return LBRACK; }
    "}"                 { return RBRACK; }
    "`"                 { return SINGLE_QUASIQUOTE; }
    "'"                 { return SINGLE_QUOTE; }
}

<YYINITIAL> {ABBREVIATION_PREFIX} { return ABBREVIATION_PREFIX; }
<YYINITIAL> {IDENTIFIER} { return VARIABLE; }
<YYINITIAL> {CHAR_LITERAL} { return CHAR_LITERAL; }
<YYINITIAL> {STRING_LITERAL} { return STRING_LITERAL; }
<YYINITIAL> {NUMBER_LITERAL} { return NUMBER_LITERAL; }

{WS} { return TokenType.WHITE_SPACE; }

. { return TokenType.BAD_CHARACTER; }