package ch.app.lalachallenge.ui.binder;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import ch.app.lalachallenge.ui.adapter.RecyclerViewAdapter;
import ch.app.lalachallenge.view.ItemIView;
import io.reactivex.functions.Action;

public class RecyclerViewBinder {

    @BindingAdapter("items")
    public static <T extends ItemIView> void setItems(RecyclerView recyclerView, ObservableList<T> list) {
        if(recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(new RecyclerViewAdapter<>(list));
        }
        else {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @BindingAdapter("itemDecoration")
    public static void setItemDecoration(RecyclerView recyclerView, boolean isShow) {
        if(isShow) {
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(recyclerView.getContext()).showLastDivider().build());
        }
    }

    @BindingAdapter("onLoadMore")
    public static void setLoadMoreListener(RecyclerView recyclerView, Action action) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int scrollThreshold = 50; //ensure the load more function call only when scrolling down
            private int visibleThreshold = 5;
            private int lastVisibleItem, totalItemCount;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (dy >= scrollThreshold && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    try {
                        action.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
