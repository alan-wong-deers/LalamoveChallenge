package ch.app.lalachallenge.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ch.app.lalachallenge.BR;
import ch.app.lalachallenge.view.ItemIView;

/**
 * Created by alanwong on 6/9/17.
 */

public class RecyclerViewAdapter<T extends ItemIView> extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<T> list;

    public RecyclerViewAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).layoutId();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding bind = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
        return new RecyclerViewHolder<>(bind);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        T model = list.get(position);
        holder.getBinding().setVariable(BR.viewModel, model);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

}
