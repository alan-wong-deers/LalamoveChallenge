package ch.app.lalachallenge.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import ch.app.lalachallenge.R;
import ch.app.lalachallenge.databinding.FragmentDeliveryListBinding;
import ch.app.lalachallenge.model.Delivery;
import ch.app.lalachallenge.view.DeliveryListFragmentIView;
import ch.app.lalachallenge.viewmodel.DeliveryListFragmentVM;
import eu.inloop.viewmodel.binding.ViewModelBaseBindingFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

public class DeliveryListFragment extends ViewModelBaseBindingFragment<DeliveryListFragmentIView, DeliveryListFragmentVM, FragmentDeliveryListBinding> implements DeliveryListFragmentIView {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_delivery_list, getContext());
    }

    @Override
    public void onDeliveryItemClick(Delivery delivery) {
        // 2 pane mode
        if(getResources().getInteger(R.integer.multi_pane_mode) == 2) {
            DeliveryDetailsFragment detailsFragment
                = (DeliveryDetailsFragment) getFragmentManager().findFragmentById(R.id.fragment_details);

            detailsFragment.getViewModel().setDelivery(delivery);
        }
        // single pane mode
        else {
            DetailsActivity.start(getContext(), delivery);
        }
    }
}
