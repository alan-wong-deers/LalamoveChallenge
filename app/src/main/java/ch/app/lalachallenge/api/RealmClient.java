package ch.app.lalachallenge.api;

import java.util.ArrayList;
import java.util.List;

import ch.app.lalachallenge.model.Delivery;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by alanwong on 6/8/17.
 */

class RealmClient {
    private static final int LIMIT = 20;

    // fetch cached deliveries
    static Observable<List<Delivery>> getDeliveries(int offset) {
        Realm realm = Realm.getDefaultInstance();
        return Observable.just(realm.copyFromRealm(realm.where(Delivery.class).findAll()))
            .filter(deliveries -> !deliveries.isEmpty())
            .map(deliveries -> {
                List<Delivery> list = new ArrayList<>();
                for(int i=offset; i<offset+LIMIT; i++) {
                    if(i >= deliveries.size()) {
                        return list;
                    }
                    list.add(deliveries.get(i));
                }
                return list;
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
