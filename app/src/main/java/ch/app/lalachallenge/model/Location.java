package ch.app.lalachallenge.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.LocationRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = { LocationRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Location.class })
public class Location extends RealmObject {
    @SerializedName("lat")
    public double lat;

    @SerializedName("lng")
    public double lng;

    @SerializedName("address")
    public String address;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
