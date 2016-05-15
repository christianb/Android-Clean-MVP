package de.bunk.usecase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.bunk.Logger;
import retrofit2.adapter.rxjava.HttpException;

import static org.mockito.Mockito.never;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpException.class)
public class DefaultSubscriberTest {
    @Mock
    private UseCase.ErrorCallback errorCallback;

    @Mock
    private Throwable throwable;

    @Mock
    private Logger logger;

    private HttpException httpException;

    private DefaultSubscriber<Object> defaultSubscriber;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        httpException = PowerMockito.mock(HttpException.class);
        Mockito.when(throwable.getCause()).thenReturn(httpException);

        defaultSubscriber = new DefaultSubscriber<>(errorCallback, logger);
    }

    @Test
    public void onError_Should_Call_OnGenericError_When_ThrowableIsNotHttpException() {
        defaultSubscriber.onError(new Throwable());

        Mockito.verify(errorCallback).onGenericError();
        Mockito.verify(errorCallback, never()).onServerError();
    }

    @Test
    public void onError_Should_Call_OnConnectionError_When_IsHttpException_And_ErrorCode_IsEqualHigher_500_And_Connected() {
        Mockito.when(httpException.code()).thenReturn(500);

        defaultSubscriber.onError(throwable);

        Mockito.verify(errorCallback, never()).onGenericError();
        Mockito.verify(errorCallback).onServerError();
    }

    @Test
    public void onError_Should_Call_OnGenericError_When_IsHttpException_And_ErrorCode_IsLessThan_500() {
        Mockito.when(httpException.code()).thenReturn(300);

        defaultSubscriber.onError(throwable);

        Mockito.verify(errorCallback).onGenericError();
        Mockito.verify(errorCallback, never()).onServerError();
    }
}