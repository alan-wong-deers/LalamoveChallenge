package ch.app.lalachallenge.viewmodel;

import ch.app.lalachallenge.R;
import ch.app.lalachallenge.model.Delivery;
import ch.app.lalachallenge.view.ItemIView;
import io.reactivex.functions.Action;

public class ItemDeliveryVM implements ItemIView {

    private Delivery mDelivery;
    private Action mAction;

    public ItemDeliveryVM(Delivery delivery, Action action) {
        mDelivery = delivery;
        mAction = action;
        if(mDelivery == null) {
            throw new NullPointerException("Delivery cannot be null");
        }
    }

    public String getDescription() {
        return mDelivery.description;
    }

    public String getAddress() {
        return mDelivery.location.address;
    }

    public String getImageUrl() {
        return mDelivery.imageUrl;
    }

    public void onItemClick() {
        try {
            mAction.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof ItemDeliveryVM)) {
            return false;
        }
        else {
            ItemDeliveryVM deliveryVM = (ItemDeliveryVM) o;
            return deliveryVM.mDelivery.id == mDelivery.id;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mDelivery.hashCode();
        result = 31 * result + mAction.hashCode();
        return result;
    }

    @Override
    public int layoutId() {
        return R.layout.item_delivery;
    }


}
