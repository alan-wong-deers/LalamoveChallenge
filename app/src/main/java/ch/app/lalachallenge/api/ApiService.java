package ch.app.lalachallenge.api;

import java.util.List;

import ch.app.lalachallenge.model.Delivery;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alanwong on 6/8/17.
 */

public interface ApiService {
    @GET("deliveries")
    Observable<List<Delivery>> getDeliveries(@Query("offset") int offset);
}
