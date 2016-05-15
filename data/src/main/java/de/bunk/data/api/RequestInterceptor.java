package de.bunk.data.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class RequestInterceptor implements Interceptor {

    private static final String OPEN_WEATHER_API_KEY = "81fdc195a16528d12b521d30a9ddc199";

    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        httpUrlBuilder.addQueryParameter("APPID", OPEN_WEATHER_API_KEY).build();

        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.url(httpUrlBuilder.build());

        return chain.proceed(requestBuilder.build());
    }
}
