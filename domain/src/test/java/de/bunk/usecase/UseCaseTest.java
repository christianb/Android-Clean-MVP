package de.bunk.usecase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CompositeSubscription.class)
public abstract class UseCaseTest<T extends UseCase> {

    @Mock
    private PostExecutionThread postExecutionThread;

    @Mock
    private SubscribeOnThread subscribeOnThread;

    private CompositeSubscription compositeSubscriptionMock;

    private T useCase;

    protected abstract T createUseCase();
    protected T getUseCase() {
        return useCase;
    }

    public UseCaseTest() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(postExecutionThread.getScheduler()).thenReturn(Schedulers.immediate());
        Mockito.when(subscribeOnThread.getScheduler()).thenReturn(Schedulers.immediate());

        compositeSubscriptionMock = PowerMockito.mock(CompositeSubscription.class);

        useCase = createUseCase();
        useCase.setPostExecutionThread(postExecutionThread);
        useCase.setSubscribeOnThread(subscribeOnThread);
        useCase.setCompisiteSubscription(compositeSubscriptionMock);
    }

    @Test
    public void cancelAll() {
        useCase.cancelAll();

        Mockito.verify(compositeSubscriptionMock).unsubscribe();
    }
}