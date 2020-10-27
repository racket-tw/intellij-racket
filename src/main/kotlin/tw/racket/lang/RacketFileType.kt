package tw.racket.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class RacketFileType private constructor() : LanguageFileType(RacketLanguage) {
    override fun getName(): String {
        return "Racket File"
    }

    override fun getDescription(): String {
        return "Racket language file"
    }

    override fun getDefaultExtension(): String {
        return "rkt"
    }

    override fun getIcon(): Icon? {
        return RacketIcons.FILE
    }

    companion object {
        @JvmField
        val INSTANCE = RacketFileType()
    }
}