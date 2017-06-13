package ch.app.lalachallenge.api;

import java.util.List;

import ch.app.lalachallenge.model.Delivery;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiClient {

    private ApiService apiService;
    private RealmClient realmClient;

    public ApiClient(RetrofitClient retrofitClient, RealmClient realmClient) {
        apiService = new ApiService(retrofitClient);
        this.realmClient = realmClient;

    }

    // merge cached and network observables
    public Observable<List<Delivery>> getDeliveries(int offset) {
        return Observable.concat(realmClient.getDeliveries(offset), apiService.getDeliveries(offset))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
