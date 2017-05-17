package com.androidessence.materialdesignspecs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidessence.materialdesignspeclibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Base adapter class for displaying colors in a RecyclerView.
 * <p>
 * Created by ankitagrawal on 5/15/17.
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseColorAdapter extends RecyclerView.Adapter<BaseColorAdapter.BaseColorViewHolder> {
    /**
     * The colors that should be displayed in this adapter.
     */
    private List<Integer> colors = new ArrayList<>();

    /**
     * The position of the currently selected color.
     */
    private int selectedPosition = RecyclerView.NO_POSITION;

    /**
     * A callback for when a color is clicked.
     */
    private ColorDialog.OnColorSelectedListener listener;

    /**
     * Base constructor.
     *
     * @param listener A callback for when a color is clicked.
     */
    public BaseColorAdapter(ColorDialog.OnColorSelectedListener listener) {
        this(new ArrayList<Integer>(), listener);
    }

    /**
     * Base constructor.
     *
     * @param colors   The colors to display in the adapter.
     * @param listener A callback for when a color is clicked.
     */
    public BaseColorAdapter(List<Integer> colors, ColorDialog.OnColorSelectedListener listener) {
        this(colors, listener, RecyclerView.NO_POSITION);
    }

    /**
     * Base constructor.
     *
     * @param colors           The colors to display in the adapter.
     * @param listener         A callback for when a color is clicked.
     * @param selectedPosition The position of the initially selected color.
     */
    public BaseColorAdapter(List<Integer> colors, ColorDialog.OnColorSelectedListener listener, int selectedPosition) {
        this.colors = colors;
        this.listener = listener;
        this.selectedPosition = selectedPosition;
    }

    @Override
    public BaseColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(getRVItemLayout(), parent, false);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseColorViewHolder holder, int position) {
        holder.bindColor(colors.get(position));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    /**
     * Abstract method that retrieves the item layout for a row in the RecyclerView.
     */
    public abstract
    @LayoutRes
    int getRVItemLayout();

    /**
     * Abstract method for the ViewHolder used by this adapter.
     */
    public abstract BaseColorViewHolder getViewHolder(View view);

    /**
     * Sets the list of colors to display in the list.
     */
    public void setColors(List<Integer> colorList) {
        colors = colorList;
    }

    /**
     * Sets the callback listener to use when a color is clicked.
     */
    public void setColorSelectedListener(ColorDialog.OnColorSelectedListener listener) {
        this.listener = listener;
    }

    /**
     * Sets the selected color position.
     */
    public void setSelectedPosition(int pos) {
        selectedPosition = pos;
        notifyDataSetChanged();
    }

    /**
     * Retrieves the position of the selected color.
     */
    public int getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * Base ViewHolder for a color adapter.
     */
    public abstract class BaseColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View colorView;
        private ImageView imgCheckView;

        public BaseColorViewHolder(View view) {
            super(view);
            initView(view);
            colorView = view;
            imgCheckView = (ImageView) view.findViewById(R.id.img_check);
            colorView.setOnClickListener(this);
        }

        /**
         * Abstract method to provide any custom initializations the user may want.
         *
         * @param view The itemView of this ViewHolder.
         */
        protected abstract void initView(View view);

        /**
         * Binds a color to this ViewHolder.
         *
         * @param color The color to display.
         */
        public void bindColor(Integer color) {
            int colorRes = ContextCompat.getColor(colorView.getContext(), color);
            int visibility = (selectedPosition == getAdapterPosition()) ? View.VISIBLE : View.GONE;

            this.colorView.setBackgroundColor(colorRes);
            this.imgCheckView.setVisibility(visibility);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION && listener != null) {
                Integer color = colors.get(getAdapterPosition());
                listener.onColorSelected(getAdapterPosition(), color);
            }
        }
    }
}
