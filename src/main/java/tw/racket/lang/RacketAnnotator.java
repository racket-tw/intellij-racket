package tw.racket.lang;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.psi.RacketBody;
import tw.racket.lang.psi.RacketDefineVar;
import tw.racket.racketclient.Message;
import tw.racket.racketclient.MessageCollector;
import tw.racket.racketclient.UnusedRequire;

import java.io.IOException;

public class RacketAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof RacketDefineVar) {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "VAR");
        }
    }
}
