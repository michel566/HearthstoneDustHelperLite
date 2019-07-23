package br.com.michelbarbosa.hearthstonedusthelperlite.api;


import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MashapeKeyInterceptor implements Interceptor {

    @Inject
    MashapeKeyInterceptor(){
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        Request newRequest = builder.addHeader("X-Mashape-Key", "YZaoaf9GcVmsh3lobS37cJ9Utd0op1eNl3fjsnbZ3RfqnDy8eA")
                .addHeader("Accept", "application/json")
                .build();
        return chain.proceed(newRequest);
    }
}
