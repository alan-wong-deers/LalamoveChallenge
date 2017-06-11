package ch.app.lalachallenge.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ch.app.lalachallenge.R;
import ch.app.lalachallenge.databinding.FragmentDeliveryDetailsBinding;
import ch.app.lalachallenge.view.DeliveryDetailsFragmentIView;
import ch.app.lalachallenge.viewmodel.DeliveryDetailsFragmentVM;
import eu.inloop.viewmodel.binding.ViewModelBaseBindingFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

/**
 * Created by alanwong on 6/9/17.
 */

public class DeliveryDetailsFragment extends ViewModelBaseBindingFragment<DeliveryDetailsFragmentIView, DeliveryDetailsFragmentVM, FragmentDeliveryDetailsBinding> implements DeliveryDetailsFragmentIView {

    private static final String MAPVIEW_BUNDLE_KEY = "MAPVIEW_BUNDLE_KEY";

    public static DeliveryDetailsFragment newInstance() {
        return new DeliveryDetailsFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        getBinding().mapView.onCreate(mapViewBundle);

        if(getResources().getInteger(R.integer.multi_pane_mode) == 1) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(R.string.title_details_fragment);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        getBinding().mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_delivery_details, getContext());
    }
}