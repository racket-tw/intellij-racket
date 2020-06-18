package tw.racket.racketclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class MessageCollector {
    private MessageCollector() {
    }

    private static MessageCollector instance = null;

    public static MessageCollector get() {
        if (instance == null) {
            instance = new MessageCollector();
        }
        return instance;
    }

    public @NotNull Message[] messages(PsiFile file) throws IOException, InterruptedException {
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
        return this.gson().fromJson(builder.toString(), Message[].class);
    }

    @NotNull
    private Gson gson() {
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
