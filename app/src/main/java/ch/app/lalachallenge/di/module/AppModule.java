package ch.app.lalachallenge.di.module;

import javax.inject.Singleton;

import ch.app.lalachallenge.App;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApp() {
        return app;
    }
}
