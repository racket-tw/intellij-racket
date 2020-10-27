package tw.racket.lang

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.jetbrains.annotations.Nls
import javax.swing.Icon

class RacketColorSettingsPage : ColorSettingsPage {
    override fun getIcon(): Icon? {
        return null
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return RacketSyntaxHighlighter()
    }

    override fun getDemoText(): String {
        return "TODO"
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
        return null
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return arrayOf(
                AttributesDescriptor("String", TextAttributesKey.createTextAttributesKey("RACKET_STRING", DefaultLanguageHighlighterColors.STRING)),
                AttributesDescriptor("Number", TextAttributesKey.createTextAttributesKey("RACKET_NUMBER", DefaultLanguageHighlighterColors.NUMBER)),
                AttributesDescriptor("Comment", TextAttributesKey.createTextAttributesKey("RACKET_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)),
                AttributesDescriptor("Braces", TextAttributesKey.createTextAttributesKey("RACKET_BRACES", DefaultLanguageHighlighterColors.BRACES)),
                AttributesDescriptor("Parentheses", TextAttributesKey.createTextAttributesKey("RACKET_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)),
                AttributesDescriptor("Bad Value", TextAttributesKey.createTextAttributesKey("RACKET_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)))
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "Racket"
    }
}