package ch.app.lalachallenge.viewmodel;

import ch.app.lalachallenge.R;
import ch.app.lalachallenge.view.ItemIView;
import io.reactivex.functions.Action;

/**
 * Created by alanwong on 6/10/17.
 */

public class ItemErrorVM implements ItemIView {

    private Action action;

    public ItemErrorVM(Action action) {
        this.action = action;
    }

    @Override
    public int layoutId() {
        return R.layout.item_error;
    }

    public void onRetryClick() {
        try {
            action.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
