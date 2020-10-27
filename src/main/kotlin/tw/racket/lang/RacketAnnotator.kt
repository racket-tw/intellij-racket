package tw.racket.lang

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import tw.racket.lang.psi.RacketDefineVar

class RacketAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is RacketDefineVar) {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "VAR")
        }
    }
}