package com.androidessence.materialdesignspecs;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.androidessence.materialdesignspeclibrary.R;

import java.util.List;

/**
 * Adapter that displays a bunch of square color choices.
 * <p>
 * Created by ankitagrawal on 5/15/17.
 */
@SuppressWarnings("WeakerAccess")
public class SquareColorAdapter extends BaseColorAdapter {

    public SquareColorAdapter(ColorDialog.OnColorSelectedListener listener) {
        super(listener);
    }

    public SquareColorAdapter(List<Integer> colors, ColorDialog.OnColorSelectedListener listener) {
        super(colors, listener);
    }

    public SquareColorAdapter(List<Integer> colors, ColorDialog.OnColorSelectedListener listener, int selectedPos) {
        super(colors, listener, selectedPos);
    }

    @Override
    public BaseColorViewHolder getViewHolder(View view) {
        return new SquareColorViewHolder(view);
    }

    @Override
    public
    @LayoutRes
    int getRVItemLayout() {
        return R.layout.list_item_color;
    }

    public class SquareColorViewHolder extends BaseColorViewHolder {
        private View colorView;
        private ImageView imgCheckView;

        public SquareColorViewHolder(View view) {
            super(view);
        }

        @Override
        protected void initView(View view) {
            colorView = view;
            imgCheckView = (ImageView) view.findViewById(R.id.img_check);
            colorView.setOnClickListener(this);
        }


        public void bindColor(Integer color) {
            int colorRes = ContextCompat.getColor(colorView.getContext(), color);
            int visibility = (getSelectedPosition() == getAdapterPosition()) ? View.VISIBLE : View.GONE;

            this.colorView.setBackgroundColor(colorRes);
            this.imgCheckView.setVisibility(visibility);
        }

    }
}
