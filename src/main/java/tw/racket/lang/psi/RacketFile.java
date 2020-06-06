package tw.racket.lang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import tw.racket.lang.RacketFileType;
import tw.racket.lang.RacketLanguage;

public class RacketFile extends PsiFileBase {
    public RacketFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, RacketLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return RacketFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Racket File";
    }
}
