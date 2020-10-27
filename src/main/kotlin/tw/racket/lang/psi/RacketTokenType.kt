package tw.racket.lang.psi

import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls
import tw.racket.lang.RacketLanguage

class RacketTokenType(@NonNls debugName: String) : IElementType(debugName, RacketLanguage) {
    override fun toString(): String {
        return "RacketTokenType." + super.toString()
    }
}