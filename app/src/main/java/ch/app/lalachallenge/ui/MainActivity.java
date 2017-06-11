package ch.app.lalachallenge.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import ch.app.lalachallenge.R;
import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;

public class MainActivity extends ViewModelBaseEmptyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(R.string.title_list_fragment);
        }
    }
}
