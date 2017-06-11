package ch.app.lalachallenge.ui.binder;

import android.databinding.BindingAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by alanwong on 6/10/17.
 */

public class MapViewBinder {
    @BindingAdapter("latlng")
    public static void setLatLng(MapView mapView, LatLng latLng) {
        mapView.getMapAsync(googleMap -> {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            googleMap.addMarker(new MarkerOptions()
                .position(latLng));
            mapView.onResume();
        });
    }
}
