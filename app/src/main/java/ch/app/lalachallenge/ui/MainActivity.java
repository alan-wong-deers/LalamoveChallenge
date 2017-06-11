package ch.app.lalachallenge.ui;

import android.os.Bundle;

import ch.app.lalachallenge.R;
import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;

public class MainActivity extends ViewModelBaseEmptyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
