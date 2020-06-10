package tw.racket.lang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.psi.RacketBody;
import tw.racket.racketclient.*;

import java.io.*;

public class RacketAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof RacketBody)) {
            return;
        }
        PsiFile file = element.getContainingFile();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("racket-service", file.getVirtualFile().getPath());
            processBuilder.directory(new File(file.getVirtualFile().getParent().getPath()));
            Process process = processBuilder.start();
            process.waitFor();
            InputStream jsonOutput = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jsonOutput));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            Message[] messages = this.gson().fromJson(builder.toString(), Message[].class);
            if (messages == null) {
                return;
            }
            for (Message msg : messages) {
                if (msg instanceof UnusedRequire) {
                    var u = (UnusedRequire) msg;
                    TextRange unusedRequireRange = TextRange.from(u.getStart(), u.getEnd() - u.getStart());
                    Annotation annotation = holder.createInfoAnnotation(unusedRequireRange, "Unused require");
                    annotation.setTextAttributes(RacketSyntaxHighlighter.BAD_CHARACTER);
                }
            }
        } catch (IllegalStateException | InterruptedException | IOException e) {
            // If fail, we have noting can do
            e.printStackTrace();
        }
    }

    @NotNull
    public Gson gson() {
        return new GsonBuilder().registerTypeAdapterFactory(this.typeFactory()).create();
    }

    private RuntimeTypeAdapterFactory<Message> typeFactory() {
        return RuntimeTypeAdapterFactory
                .of(Message.class, "type")
                .registerSubtype(UnusedRequire.class)
                .registerSubtype(NewDefinition.class)
                .registerSubtype(Ignore.class)
                .registerSubtype(OnHoverInfo.class);
    }
}
