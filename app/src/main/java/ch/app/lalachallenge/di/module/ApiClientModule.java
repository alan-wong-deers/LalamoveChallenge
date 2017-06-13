package ch.app.lalachallenge.di.module;

import javax.inject.Singleton;

import ch.app.lalachallenge.api.ApiClient;
import ch.app.lalachallenge.api.RealmClient;
import ch.app.lalachallenge.api.RetrofitClient;
import dagger.Module;
import dagger.Provides;

@Module
public class ApiClientModule {

    private String baseUrl;

    public ApiClientModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    RetrofitClient provideRetrofitClient() {
        return new RetrofitClient(baseUrl);
    }

    @Provides
    @Singleton
    RealmClient provideRealmClient() {
        return new RealmClient();
    }

    @Provides
    @Singleton
    ApiClient provideApiClient(RetrofitClient retrofitClient, RealmClient realmClient) {
        return new ApiClient(retrofitClient, realmClient);
    }
}
