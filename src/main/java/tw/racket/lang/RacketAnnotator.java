package tw.racket.lang;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.psi.RacketBody;
import tw.racket.racketclient.Message;
import tw.racket.racketclient.MessageCollector;
import tw.racket.racketclient.UnusedRequire;

import java.io.IOException;

public class RacketAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof RacketBody)) {
            return;
        }
        Message[] messages = new Message[0];
        try {
            messages = MessageCollector.get().messages(element.getContainingFile());
        } catch (IllegalStateException | InterruptedException | IOException e) {
            // If fail, we have noting can do
            e.printStackTrace();
        }
        for (Message msg : messages) {
            if (msg instanceof UnusedRequire) {
                var u = (UnusedRequire) msg;
                TextRange unusedRequireRange = TextRange.from(u.getStart(), u.getEnd() - u.getStart());
                Annotation annotation = holder.createInfoAnnotation(unusedRequireRange, "Unused require");
                annotation.setTextAttributes(RacketSyntaxHighlighter.BAD_CHARACTER);
            }
        }
    }
}
