package ch.app.lalachallenge.di.component;

import javax.inject.Singleton;

import ch.app.lalachallenge.di.module.ApiClientModule;
import ch.app.lalachallenge.viewmodel.DeliveryListFragmentVM;
import dagger.Component;

@Singleton
@Component(modules = {ApiClientModule.class})
public interface ApiClientComponent {
    void inject(DeliveryListFragmentVM deliveryListFragmentVM);
}
