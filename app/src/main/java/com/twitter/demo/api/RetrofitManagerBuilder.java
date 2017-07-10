package com.twitter.demo.api;

import android.util.Log;

import com.twitter.demo.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 * <p/>
 * If you like to go in details about the retrofit , please visit this link
 * https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit
 */
public class RetrofitManagerBuilder {

    /**
     * it would be more better if we used the Builder design pattern
     */
    public static RetrofitManagerComposer with(RetrofitType type) {
        return new RetrofitManagerComposer(type);
    }


    public static final class RetrofitManagerComposer {

        private  RetrofitType mRetrofitType;

        private RetrofitManagerComposer( RetrofitType type) {
            mRetrofitType = type;
        }

        public Retrofit build() {

            // Build the retrofit with out flavor . Just Normal
            if (mRetrofitType ==  RetrofitType.Normal) {
                return new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(getNormalGsonConverter())
                        .addCallAdapterFactory(getRXAndroidAdapter())
//                        .client(getOKHTTPClient())
                        .client(getLogger())
                        .build();
            }
            // Build The retrofit with Custom Gson to Custom handling the data
            else if (mRetrofitType ==  RetrofitType.WithCustomGson) {
                return new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(getCustomGsonConverter())
                        .addCallAdapterFactory(getRXAndroidAdapter())
                        .build();
            }
          else if (mRetrofitType ==  RetrofitType.Logger) {
                return new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addCallAdapterFactory(getRXAndroidAdapter())
                        .client(getLogger())
                        .build();
            }
            // Build The retrofit with Hybird Mode Contains all the favors.
            else {
                return new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(getNormalGsonConverter())
                        .addCallAdapterFactory(getRXAndroidAdapter())
                        .build();
            }
        }


        /**
         * If you wish to default com.apptcom.blublue.blublue.network calls to be asynchronous, you need to use createWithScheduler().
         *
         * @return CallAdapter.Factory
         */
        private CallAdapter.Factory getRXAndroidAdapter() {
            return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        }


        /**
         * we need to specify a factory for deserializing the response using the Gson library
         * by using the default handler
         *
         * @return GsonConverterFactory
         */
        private GsonConverterFactory getNormalGsonConverter() {
            return GsonConverterFactory.create();
        }


        /**
         * we need to specify a factory for deserializing the response using the Gson library
         * by using the custom handler.
         *
         * @return GsonConverterFactory
         */
        private GsonConverterFactory getCustomGsonConverter() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            return GsonConverterFactory.create(gson);
        }

        /**
         * Retrofit and OkHttp can be hard to troubleshoot when trying to step through the various layers of abstraction in the libraries.
         *
         * @return OkHttpClient.Builder
         */
        private OkHttpClient getLogger() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            // Can be Level.BASIC, Level.HEADERS, or Level.BODY
            // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);
            return builder.build();
        }
    }

    private static OkHttpClient getOKHTTPClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        // try the request
                        Response response = chain.proceed(request);
                        int tryCount = 1;
                        while (!response.isSuccessful() && tryCount == 1) {
                            Log.d("intercept", "Request is not successful - " + tryCount);
                            tryCount++;
                            // retry the request
                            response = chain.proceed(request);
                        }
                        // otherwise just pass the original response on
                        return response;
                    }
                });
        return builder.build();
    }
}
