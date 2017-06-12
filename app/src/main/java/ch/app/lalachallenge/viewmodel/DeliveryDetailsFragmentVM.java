package ch.app.lalachallenge.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import ch.app.lalachallenge.model.Delivery;
import ch.app.lalachallenge.view.DeliveryDetailsFragmentIView;
import eu.inloop.viewmodel.AbstractViewModel;
import io.realm.Realm;

/**
 * Created by alanwong on 6/9/17.
 */

public class DeliveryDetailsFragmentVM extends AbstractViewModel<DeliveryDetailsFragmentIView> {

    public static final String DELIVERY_BUNDLE_KEY = "DELIVERY_BUNDLE_KEY";

    // public fields for layout binding
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> address = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<LatLng> latLng = new ObservableField<>();
    public ObservableInt detailsVisibility = new ObservableInt(View.VISIBLE);

    private Realm realm;

    @Override
    public void onCreate(Bundle arguments, Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        realm = Realm.getDefaultInstance();
        if(realm.where(Delivery.class).count() > 0) {
            setDefaultDelivery();
        }
        else {
            detailsVisibility.set(View.GONE);
            realm.addChangeListener(realmChanged -> {
                // listen for realm change only for the first batch of data
                // no need to listen for the comming changes
                realmChanged.removeAllChangeListeners();
                setDefaultDelivery();
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //in case the activity destroy before realm change
        realm.removeAllChangeListeners();
        realm.close();
    }

    public void setDelivery(Delivery delivery) {
        // bind the data fields
        description.set(delivery.description);
        address.set(delivery.location.address);
        imageUrl.set(delivery.imageUrl);
        latLng.set(new LatLng(delivery.location.lat, delivery.location.lng));

        // change cardview visibility and play animation
        detailsVisibility.set(View.VISIBLE);
        getViewOptional().playAnimation();
    }

    private void setDefaultDelivery() {
        setDelivery(realm.where(Delivery.class).findAll().first());
    }
}
