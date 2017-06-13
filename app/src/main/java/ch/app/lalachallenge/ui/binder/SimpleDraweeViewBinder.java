package ch.app.lalachallenge.ui.binder;

import android.databinding.BindingAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

public class SimpleDraweeViewBinder {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(SimpleDraweeView simpleDraweeView, String imageUrl) {
        simpleDraweeView.setImageURI(imageUrl);
    }
}
