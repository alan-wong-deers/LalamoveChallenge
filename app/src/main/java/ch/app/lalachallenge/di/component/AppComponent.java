package ch.app.lalachallenge.di.component;

import javax.inject.Singleton;

import ch.app.lalachallenge.di.module.AppModule;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
interface AppComponent {

}
