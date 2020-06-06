package tw.racket.lang;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.lexer.RacketLexerAdapter;
import tw.racket.lang.psi.RacketTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class RacketSyntaxHighlighter extends SyntaxHighlighterBase {
    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new RacketLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(RacketTypes.STRING_LITERAL)) {
            return STRING_KEYS;
        } else if (tokenType.equals(RacketTypes.NUMBER_LITERAL)) {
            return NUMBER_KEYS;
        } else if (tokenType.equals(RacketTypes.CHAR_LITERAL)) {
            return STRING_KEYS;
        } else if (tokenType.equals(RacketTypes.KEYWORD)) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(RacketTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }

    public static final TextAttributesKey STRING =
            createTextAttributesKey("RACKET_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("RACKET_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("RACKET_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("RACKET_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("RACKET_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
}
