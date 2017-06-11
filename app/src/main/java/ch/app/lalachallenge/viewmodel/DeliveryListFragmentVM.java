package ch.app.lalachallenge.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import ch.app.lalachallenge.api.ApiClient;
import ch.app.lalachallenge.model.Delivery;
import ch.app.lalachallenge.view.DeliveryListFragmentIView;
import ch.app.lalachallenge.view.ItemIView;
import eu.inloop.viewmodel.AbstractViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * Created by alanwong on 6/9/17.
 */

public class DeliveryListFragmentVM extends AbstractViewModel<DeliveryListFragmentIView> {

    private boolean isLoading = false;
    private Disposable disposable;

    // public fiels expose to layout bindings
    public ObservableList<ItemIView> list = new ObservableArrayList<>();
    public ObservableInt progressVisibility = new ObservableInt(View.GONE);
    public ObservableInt errorVisibility = new ObservableInt(View.GONE);
    public ObservableBoolean refreshing = new ObservableBoolean(false);

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
    }

    @Override
    public void onBindView(@NonNull DeliveryListFragmentIView view) {
        super.onBindView(view);
        if(list.isEmpty()) {
            loadDeliveries();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(disposable != null) {
            disposable.dispose();
        }
    }

    public Action onLoadMore = () -> {
        if(!isLoading) {
            //Log.d("debug", "load more");
            //isLoading = true;
            loadDeliveries();
        }
    };

    public void onRefresh() {
        refreshing.set(true);
        if(!isLoading) {
            list.clear();
            loadDeliveries();
        }
        else {
            // do nothing if loading
            refreshing.set(false);
        }
    }

    private void loadDeliveries() {
        errorVisibility.set(View.GONE);
        if(list.isEmpty()) {
            progressVisibility.set(View.VISIBLE);
        }

        isLoading = true;
        disposable = ApiClient.getDeliveries(list.isEmpty() ? 0 : list.size()-1)
            .subscribe(
                this::onNext,
                this::onError,
                () -> {}//Log.d("debug", "onComplete()")
            );
    }

    private void onNext(List<Delivery> deliveries) {
        //Log.d("debug", "onnext");
        // convert List<Delivery> to List<ItemIView>
        Observable.just(deliveries)
            .flatMapIterable(list -> list)
            .map(delivery -> new ItemDeliveryVM(delivery, () -> getViewOptional().onDeliveryItemClick(delivery)))
            .toList()
            .subscribe(itemDeliveryVMs -> {
                refreshing.set(false);
                if(!list.containsAll(itemDeliveryVMs)) {
                    isLoading = false;
                    progressVisibility.set(View.GONE);
                    if(!list.isEmpty()) {
                        list.remove(list.size() - 1);
                    }

                    list.addAll(itemDeliveryVMs);
                    list.add(new ItemProgressVM());
                }

                //Log.d("debug", "list size = " + list.size());
            });
    }

    private void onError(Throwable throwable) {
        //Log.d("debug", "onError() : " + throwable.getMessage());
        isLoading = false;
        refreshing.set(false);
        progressVisibility.set(View.GONE);
        if(!list.isEmpty()) {
            list.remove(list.size() - 1); //remove the loading item
            list.add(new ItemErrorVM(() -> {
                list.remove(list.size() - 1); // remove the loading item
                list.add(new ItemProgressVM());
                onLoadMore.run();
            }));
        }
        else {
            errorVisibility.set(View.VISIBLE);
        }
    }
}
