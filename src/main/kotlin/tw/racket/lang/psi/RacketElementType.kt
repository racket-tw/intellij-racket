package tw.racket.lang.psi

import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls
import tw.racket.lang.RacketLanguage

class RacketElementType(@NonNls debugName: String) : IElementType(debugName, RacketLanguage)