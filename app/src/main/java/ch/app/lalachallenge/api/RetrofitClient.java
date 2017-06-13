package ch.app.lalachallenge.api;

import ch.app.lalachallenge.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit retrofit;

    //"http://lavamovechallenge.eu-2.evennode.com/"
    // fetch network deliveries api
    public RetrofitClient(String baseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build();
    }

    /**
     * Creates an implementation of the API defined by the ApiInterfaceClass
     * @param apiInterfaceClass
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> apiInterfaceClass) {
        return retrofit.create(apiInterfaceClass);
    }

}
