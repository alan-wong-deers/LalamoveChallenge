package ch.app.lalachallenge.ui.binder;

import android.databinding.BindingAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by alanwong on 6/9/17.
 */

public class SimpleDraweeViewBinder {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(SimpleDraweeView simpleDraweeView, String imageUrl) {
        simpleDraweeView.setImageURI(imageUrl);
    }
}
