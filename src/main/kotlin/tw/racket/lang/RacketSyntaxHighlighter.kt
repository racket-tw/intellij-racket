package tw.racket.lang

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import tw.racket.lang.lexer.RacketLexerAdapter
import tw.racket.lang.psi.RacketTypes

class RacketSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return RacketLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return if (tokenType == RacketTypes.STRING_LITERAL) {
            STRING_KEYS
        } else if (tokenType == RacketTypes.NUMBER_LITERAL) {
            NUMBER_KEYS
        } else if (tokenType == RacketTypes.CHAR_LITERAL) {
            STRING_KEYS
        } else if (tokenType == RacketTypes.COMMENT) {
            COMMENT_KEYS
        } else if (tokenType == RacketTypes.TRUE || tokenType == RacketTypes.FALSE) {
            BOOLEAN_KEYS
        } else if (tokenType == RacketTypes.MODULE_PLUS || tokenType == RacketTypes.PROVIDE || tokenType == RacketTypes.DEFINE || tokenType == RacketTypes.BEGIN || tokenType == RacketTypes.LET || tokenType == RacketTypes.LET_STAR || tokenType == RacketTypes.LET_REC || tokenType == RacketTypes.STRUCT || tokenType == RacketTypes.COND || tokenType == RacketTypes.MATCH) {
            KEYWORD_KEYS
        } else if (tokenType == TokenType.BAD_CHARACTER) {
            BAD_CHAR_KEYS
        } else if (tokenType == RacketTypes.LPAREN || tokenType == RacketTypes.RPAREN) {
            PARENTHESES_KEYS
        } else if (tokenType == RacketTypes.LBRACE || tokenType == RacketTypes.RBRACE) {
            BRACES_KEYS
        } else {
            arrayOf()
        }
    }

    companion object {
        private val BRACES_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey("RACKET_BRACES", DefaultLanguageHighlighterColors.BRACES))
        private val PARENTHESES_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey("RACKET_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES))
        private val KEYWORD_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey("RACKET_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD))
        private val STRING_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey("RACKET_STRING", DefaultLanguageHighlighterColors.STRING))
        private val NUMBER_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey("RACKET_NUMBER", DefaultLanguageHighlighterColors.NUMBER))
        private val BOOLEAN_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey("RACKET_BOOLEAN", DefaultLanguageHighlighterColors.CONSTANT))
        private val BAD_CHAR_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey("RACKET_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER))
        private val COMMENT_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey("RACKET_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT))
    }
}