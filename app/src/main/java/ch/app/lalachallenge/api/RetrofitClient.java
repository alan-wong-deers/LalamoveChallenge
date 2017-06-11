package ch.app.lalachallenge.api;

import java.util.List;

import ch.app.lalachallenge.BuildConfig;
import ch.app.lalachallenge.model.Delivery;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alanwong on 6/8/17.
 */

class RetrofitClient {

    private static RetrofitClient mInstance;
    private ApiService mApiService;

    static RetrofitClient getInstance() {
        if(mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    // constructor
    private RetrofitClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://lavamovechallenge.eu-2.evennode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build();

        mApiService = retrofit.create(ApiService.class);
    }

    /*
    * api calls implements
    * */

    // fetch network deliveries api
    Observable<List<Delivery>> getDeliveries(int offset) {
        return mApiService.getDeliveries(offset)
            .doOnNext(deliveries -> {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(realmTran -> {
                    int id = offset;
                    for(Delivery delivery : deliveries) {
                        delivery.id = id++;
                        realmTran.insertOrUpdate(delivery);
                    }
                });
                realm.close();
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
