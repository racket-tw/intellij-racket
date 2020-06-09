package tw.racket.lang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.psi.RacketBody;
import tw.racket.racketclient.Message;
import tw.racket.racketclient.UnusedRequire;

import java.io.IOException;
import java.io.OutputStream;

public class RacketAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof RacketBody)) {
            return;
        }
        PsiFile file = element.getContainingFile();
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("racket-service " + file.getVirtualFile().getPath());
            OutputStream jsonOutput = process.getOutputStream();
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(this.typeFactory()).create();
            Message[] messages = gson.fromJson(String.valueOf(jsonOutput), Message[].class);
            for (Message msg : messages) {
                if (msg instanceof UnusedRequire) {
                    var u = (UnusedRequire) msg;
                    TextRange unusedRequireRange = TextRange.from(u.startPosition, u.endPosition - u.startPosition);

                    holder.createInfoAnnotation(unusedRequireRange, "Unused require");
                }
            }
        } catch (IOException e) {
            // If fail, we have noting can do
            e.printStackTrace();
        }
    }

    private RuntimeTypeAdapterFactory<Message> typeFactory() {
        return RuntimeTypeAdapterFactory
                .of(Message.class, "type")
                .registerSubtype(UnusedRequire.class, UnusedRequire.TYPE);
    }
}
