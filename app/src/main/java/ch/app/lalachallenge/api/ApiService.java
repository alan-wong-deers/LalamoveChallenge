package ch.app.lalachallenge.api;

import java.util.List;

import ch.app.lalachallenge.model.Delivery;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.http.GET;
import retrofit2.http.Query;

class ApiService {

    private IApiService mApiService;

    ApiService(RetrofitClient retrofitClient) {
        mApiService = retrofitClient.create(IApiService.class);
    }

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

    interface IApiService {
        @GET("deliveries")
        Observable<List<Delivery>> getDeliveries(@Query("offset") int offset);
    }

}
