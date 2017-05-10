package com.androidessence.materialdesignspecs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidessence.materialdesignspeclibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Dialog fragment that displays a list of colors.
 *
 * Created by adam.mcneilly on 5/8/17.
 */
public class ColorDialog extends DialogFragment {

    private OnColorSelectedListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_color, container, false);

        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            ColorAdapter adapter = new ColorAdapter(MaterialPalettes.getColorsByLevel(MaterialPalettes.LEVEL_500));

            RecyclerView recyclerView = (RecyclerView) view;
            if (recyclerView != null) {
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        } catch (IllegalAccessException iae) {
            Log.e(ColorDialog.class.getSimpleName(), iae.getMessage(), iae);
        }

        return view;
    }

    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnColorSelectedListener {
        void onColorSelected(Integer color);
    }

    @SuppressWarnings("WeakerAccess")
    public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
        private List<Integer> colors = new ArrayList<>();

        public ColorAdapter(List<Integer> colors) {
            this.colors = colors;
        }

        @Override
        public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_color, parent, false);
            return new ColorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ColorViewHolder holder, int position) {
            holder.bindColor(colors.get(position));
        }

        @Override
        public int getItemCount() {
            return colors.size();
        }

        public class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private View colorView;

            public ColorViewHolder(View view) {
                super(view);

                colorView = view;
                colorView.setOnClickListener(this);
            }

            public void bindColor(Integer color) {
                int colorRes = ContextCompat.getColor(colorView.getContext(), color);

                this.colorView.setBackgroundColor(colorRes);
            }

            @Override
            public void onClick(View v) {
                Integer color = colors.get(getAdapterPosition());

                if (listener != null) {
                    listener.onColorSelected(color);
                }

                dismiss();
            }
        }
    }
}
