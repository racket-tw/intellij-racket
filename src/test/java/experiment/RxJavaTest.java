package experiment;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;
import org.junit.Assert;
import org.junit.Test;

public class RxJavaTest {
    @Test
    public void flow() {
        Flowable.just("Hello world").blockingSubscribe(
                new DisposableSubscriber<>() {
                    @Override
                    public void onNext(String s) {
                        Assert.assertEquals("Hello world", s);
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
