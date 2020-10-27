package tw.racket.lang;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.psi.RacketTypes;

public class RacketCompletionContributor extends CompletionContributor {
    private static final String[] keywords = new String[]{"define", "if", "cond", "else", "unquote", "unquote-splicing", "quote", "lambda", "if", "set!", "begin", "cond", "and", "or", "case", "let", "let*", "letrec", "do", "delay", "quasiquote"};

    public RacketCompletionContributor() {
        this.extend(CompletionType.SMART, PlatformPatterns.psiElement(RacketTypes.VARIABLE).withLanguage(RacketLanguage.INSTANCE), new CompletionProvider<>() {
            @Override
            public void addCompletions(@NotNull CompletionParameters parameters,
                                       @NotNull ProcessingContext context,
                                       @NotNull CompletionResultSet resultSet) {
                for (String keyword : keywords) {
                    resultSet.addElement(LookupElementBuilder.create(keyword));
                }
            }
        });
    }
}
