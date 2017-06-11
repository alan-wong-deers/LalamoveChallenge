package ch.app.lalachallenge.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.DeliveryRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alanwong on 6/8/17.
 */

@Parcel(implementations = { DeliveryRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Delivery.class })
public class Delivery extends RealmObject {
    @PrimaryKey
    public int id;

    @SerializedName("description")
    public String description;

    @SerializedName("imageUrl")
    public String imageUrl;

    @SerializedName("location")
    public Location location;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
