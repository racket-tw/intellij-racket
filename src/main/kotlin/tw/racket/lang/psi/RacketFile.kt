package tw.racket.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import tw.racket.lang.RacketLanguage
import tw.racket.lang.RacketFileType

class RacketFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RacketLanguage) {
    override fun getFileType(): FileType {
        return RacketFileType
    }

    override fun toString(): String {
        return "Racket File"
    }
}