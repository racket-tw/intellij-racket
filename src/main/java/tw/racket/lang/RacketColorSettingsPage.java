package tw.racket.lang;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class RacketColorSettingsPage implements ColorSettingsPage {
    @Nullable
    @Override
    public Icon getIcon() {
        return null;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new RacketSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "TODO";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return new AttributesDescriptor[]{
                new AttributesDescriptor("String", RacketSyntaxHighlighter.STRING),
                new AttributesDescriptor("Number", RacketSyntaxHighlighter.NUMBER),
                new AttributesDescriptor("Comment", RacketSyntaxHighlighter.COMMENT),
                new AttributesDescriptor("Braces", RacketSyntaxHighlighter.BRACES),
                new AttributesDescriptor("Parentheses", RacketSyntaxHighlighter.PARENTHESES),
                new AttributesDescriptor("Bad Value", RacketSyntaxHighlighter.BAD_CHARACTER),
        };
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @NotNull
    @Override
    public String getDisplayName() {
        return "Racket";
    }
}
