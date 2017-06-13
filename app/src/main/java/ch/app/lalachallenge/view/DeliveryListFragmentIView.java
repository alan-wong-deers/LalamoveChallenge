package ch.app.lalachallenge.view;

import ch.app.lalachallenge.model.Delivery;
import eu.inloop.viewmodel.IView;

public interface DeliveryListFragmentIView extends IView {
    void onDeliveryItemClick(Delivery delivery);
}
