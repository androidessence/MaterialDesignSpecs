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
 * Created by ankitagrawal on 5/15/17.
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseColorAdapter extends RecyclerView.Adapter<BaseColorAdapter.BaseColorViewHolder> {
    private List<Integer> colors = new ArrayList<>();
    private int selectedPosition = RecyclerView.NO_POSITION;

    private ColorDialog.OnColorSelectedListener listener;

    public BaseColorAdapter(List<Integer> colors, ColorDialog.OnColorSelectedListener listener) {
        this(colors, listener, RecyclerView.NO_POSITION);
    }

    public BaseColorAdapter(ColorDialog.OnColorSelectedListener listener) {
        this(new ArrayList<Integer>(), listener, RecyclerView.NO_POSITION);
    }

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

    public void setSelected(int pos) {
        selectedPosition = pos;
        notifyDataSetChanged();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public abstract
    @LayoutRes
    int getRVItemLayout();

    public abstract BaseColorViewHolder getViewHolder(View view);

    public void setColors(List<Integer> colorList) {
        colors = colorList;
    }

    public void setColorSelectedListener(ColorDialog.OnColorSelectedListener listener) {
        this.listener = listener;
    }

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

        protected abstract void initView(View view);

        public void bindColor(Integer color) {
            int colorRes = ContextCompat.getColor(colorView.getContext(), color);
            if (selectedPosition == getAdapterPosition()) {
                imgCheckView.setVisibility(View.VISIBLE);
            } else {
                imgCheckView.setVisibility(View.GONE);
            }
            this.colorView.setBackgroundColor(colorRes);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }
            Integer color = colors.get(getAdapterPosition());

            if (listener != null) {
                listener.onColorSelected(getAdapterPosition(), color);
            }

        }
    }
}
