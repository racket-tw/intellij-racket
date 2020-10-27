package tw.racket.lang;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.psi.RacketTypes;

public class RacketCompletionContributor extends CompletionContributor {
    private static final String[] keywords = new String[]{"(define )", "(if )", "(cond [else ])", "[else ]", "(lambda () )", "(set! )", "(begin )", "and", "or", "(let ())", "(let* () )", "(letrec () )", "(struct ())"};

    public RacketCompletionContributor() {
        this.extend(CompletionType.BASIC, PlatformPatterns.psiElement(RacketTypes.VARIABLE).withLanguage(RacketLanguage.INSTANCE), new CompletionProvider<>() {
            @Override
            public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
                for (String keyword : keywords) {
                    resultSet.addElement(LookupElementBuilder.create(keyword));
                }
            }
        });
    }
}
