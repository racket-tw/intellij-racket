package tw.racket.lang.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.RacketLanguage;

public class RacketElementType extends IElementType {
    public RacketElementType(@NotNull @NonNls String debugName) {
        super(debugName, RacketLanguage.INSTANCE);
    }
}
