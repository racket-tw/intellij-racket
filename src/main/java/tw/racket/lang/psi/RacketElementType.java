package tw.racket.lang.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tw.racket.lang.RacketLanguage;

public class RacketElementType extends IElementType {
    public RacketElementType(@NotNull String debugName, @Nullable Language language) {
        super(debugName, RacketLanguage.INSTANCE);
    }
}
