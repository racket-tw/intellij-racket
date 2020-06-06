package tw.racket.lang.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tw.racket.lang.RacketLanguage;

public class RacketTokenType extends IElementType {
    public RacketTokenType(@NotNull String debugName, @Nullable Language language) {
        super(debugName, RacketLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "RacketTokenType." + super.toString();
    }
}
