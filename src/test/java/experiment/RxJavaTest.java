package experiment;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;
import org.junit.Assert;
import org.junit.Test;

public class RxJavaTest {
    @Test
    public void flow() {
        Flowable.fromSupplier(() -> {
            int a = 1;
            return String.format("number %d", a);
        }).blockingSubscribe(
                new DisposableSubscriber<>() {
                    @Override
                    public void onNext(String s) {
                        Assert.assertEquals("number 1", s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Assert.fail();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }
}
