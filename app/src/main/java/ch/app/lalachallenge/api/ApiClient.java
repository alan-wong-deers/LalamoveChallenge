package ch.app.lalachallenge.api;

import java.util.List;

import ch.app.lalachallenge.model.Delivery;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alanwong on 6/8/17.
 */

public class ApiClient {
    // merge cached and network observables
    public static Observable<List<Delivery>> getDeliveries(int offset) {
        return Observable.concat(RealmClient.getDeliveries(offset), RetrofitClient.getInstance().getDeliveries(offset))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
