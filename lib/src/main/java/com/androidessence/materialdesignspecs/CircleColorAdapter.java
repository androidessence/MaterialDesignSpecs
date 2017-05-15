package com.androidessence.materialdesignspecs;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.androidessence.materialdesignspeclibrary.R;

import java.util.List;

/**
 * Created by ankitagrawal on 5/15/17.
 */
public class CircleColorAdapter extends BaseColorAdapter {
    public CircleColorAdapter(List<Integer> colors, ColorDialog.OnColorSelectedListener listener) {
        super(colors, listener);
    }
    public CircleColorAdapter(List<Integer> colors,ColorDialog.OnColorSelectedListener listener, int selectedPos) {
        super(colors, listener, selectedPos);
    }
    public CircleColorAdapter(ColorDialog.OnColorSelectedListener listener) {
        super(listener);
    }
    @Override
    public int getRVItemLayout() {
        return R.layout.circle_list_item_color;
    }

    @Override
    public BaseColorViewHolder getViewHolder(View view) {
        return new CircleColorViewHolder(view);
    }
    public class CircleColorViewHolder extends BaseColorViewHolder {
        private View colorView;
        private ImageView imgCheckView;
        public CircleColorViewHolder(View view) {
            super(view);
        }

        @Override
        protected void initView(View view) {
            colorView = view;
            imgCheckView = (ImageView) view.findViewById(R.id.img_check);
            colorView.setOnClickListener(this);
        }

        private Drawable createSelector(int color) {
            ShapeDrawable coloredCircle = new ShapeDrawable(new OvalShape());
            coloredCircle.getPaint().setColor(color);
            ShapeDrawable darkerCircle = new ShapeDrawable(new OvalShape());
            darkerCircle.getPaint().setColor(shiftColor(color));

            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, coloredCircle);
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, darkerCircle);
            return stateListDrawable;
        }
        private int shiftColor(int color) {
            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[2] *= 0.9f; // value component
            return Color.HSVToColor(hsv);
        }
        public void bindColor(Integer colorRes) {
            int color = ContextCompat.getColor(colorView.getContext(), colorRes);
            if(getSelected() == getAdapterPosition()) {
                imgCheckView.setVisibility(View.VISIBLE);
            }else{
                imgCheckView.setVisibility(View.GONE);
            }
            Drawable selector = createSelector(color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int[][] states = new int[][]{
                        new int[]{-android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_pressed}
                };
                int[] colors = new int[]{
                        shiftColor(color),
                        color
                };
                ColorStateList rippleColors = new ColorStateList(states, colors);
                setBackgroundCompat(colorView, new RippleDrawable(rippleColors, selector, null));
            } else {
                setBackgroundCompat(colorView, selector);
            }
        }
        @SuppressWarnings("deprecation")
        private void setBackgroundCompat(View view, Drawable d) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                view.setBackground(d);
            else view.setBackgroundDrawable(d);
        }

    }
}
