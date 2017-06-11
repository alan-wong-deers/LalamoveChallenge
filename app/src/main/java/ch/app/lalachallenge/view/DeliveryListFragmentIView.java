package ch.app.lalachallenge.view;

import ch.app.lalachallenge.model.Delivery;
import eu.inloop.viewmodel.IView;

/**
 * Created by alanwong on 6/9/17.
 */

public interface DeliveryListFragmentIView extends IView {
    void onDeliveryItemClick(Delivery delivery);
}
