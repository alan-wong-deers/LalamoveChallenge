package ch.app.lalachallenge.ui.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by alanwong on 6/9/17.
 */

class RecyclerViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private V v;

    RecyclerViewHolder(V v) {
        super(v.getRoot());
        this.v = v;
    }

    public V getBinding() {
        return v;
    }
}
