package ch.app.lalachallenge;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import io.realm.Realm;

/**
 * Created by alanwong on 6/8/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
            .setDownsampleEnabled(true) // enable downsample to lower memory usage
            .build();
        Fresco.initialize(this, config);

        Realm.init(this);
    }
}
