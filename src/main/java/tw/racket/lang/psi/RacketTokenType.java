package tw.racket.lang.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.RacketLanguage;

public class RacketTokenType extends IElementType {
    public RacketTokenType(@NotNull @NonNls String debugName) {
        super(debugName, RacketLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "RacketTokenType." + super.toString();
    }
}
