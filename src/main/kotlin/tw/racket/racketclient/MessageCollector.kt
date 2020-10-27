package tw.racket.racketclient

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import com.intellij.psi.PsiFile
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

class MessageCollector private constructor() {
    fun messages(file: PsiFile): Array<Message> {
        val processBuilder = ProcessBuilder()
        processBuilder.command("racket", "-l", "racket-service", file.virtualFile.path)
        processBuilder.directory(File(file.virtualFile.parent.path))
        val process = processBuilder.start()
        process.waitFor()
        val jsonOutput = process.inputStream
        val reader = BufferedReader(InputStreamReader(jsonOutput))
        val builder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            builder.append(line)
        }
        return gson().fromJson(builder.toString(), Array<Message>::class.java)
    }

    private fun gson(): Gson {
        return GsonBuilder().registerTypeAdapterFactory(typeFactory()).create()
    }

    private fun typeFactory(): RuntimeTypeAdapterFactory<Message> {
        return RuntimeTypeAdapterFactory
                .of(Message::class.java, "type")
                .registerSubtype(UnusedRequire::class.java)
                .registerSubtype(NewDefinition::class.java)
                .registerSubtype(Ignore::class.java)
                .registerSubtype(OnHoverInfo::class.java)
    }

    companion object {
        private var instance: MessageCollector? = null
        fun get(): MessageCollector? {
            if (instance == null) {
                instance = MessageCollector()
            }
            return instance
        }
    }
}