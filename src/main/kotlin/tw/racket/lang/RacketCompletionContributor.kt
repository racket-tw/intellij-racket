package tw.racket.lang

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import tw.racket.lang.psi.RacketTypes

class RacketCompletionContributor : CompletionContributor() {
    companion object {
        private val keywords = arrayOf("(define )", "(if )", "(cond [else ])", "[else ]", "(lambda () )", "(set! )", "(begin )", "and", "or", "(let ())", "(let* () )", "(letrec () )", "(struct ())")
    }

    init {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(RacketTypes.VARIABLE).withLanguage(RacketLanguage), object : CompletionProvider<CompletionParameters>() {
            public override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, resultSet: CompletionResultSet) {
                for (keyword in keywords) {
                    resultSet.addElement(LookupElementBuilder.create(keyword))
                }
            }
        })
    }
}