package ch.app.lalachallenge.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import org.parceler.Parcels;

import ch.app.lalachallenge.R;
import ch.app.lalachallenge.model.Delivery;
import ch.app.lalachallenge.viewmodel.DeliveryDetailsFragmentVM;
import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;

/**
 * Created by alanwong on 6/11/17.
 */

public class DetailsActivity extends ViewModelBaseEmptyActivity {

    public static void start(Context context, Delivery delivery) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(
            DeliveryDetailsFragmentVM.DELIVERY_BUNDLE_KEY,
            Parcels.wrap(delivery)
        );
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setupActionBar();

        DeliveryDetailsFragment detailsFragment
            = (DeliveryDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        Delivery delivery = Parcels.unwrap(getIntent().getParcelableExtra(DeliveryDetailsFragmentVM.DELIVERY_BUNDLE_KEY));
        detailsFragment.getViewModel().setDelivery(delivery);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
