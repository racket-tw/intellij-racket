package experiment;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import org.junit.Assert;
import org.junit.Test;

public class RxJavaTest {
    @Test
    public void flow() {
        Flowable.fromSupplier(() -> {
            int a = 1;
            return String.format("number %d", a);
        }).blockingSubscribe(
                (s) -> Assert.assertEquals("number 1", s),
                (err) -> Assert.fail()
        );
    }

    @Test
    public void observable() {
        Observable.fromSupplier(() -> {
            int a = 1;
            return String.format("number %d", a);
        }).blockingSubscribe(
                (next) -> Assert.assertEquals("number 1", next),
                (err) -> Assert.fail()
        );
    }
}
