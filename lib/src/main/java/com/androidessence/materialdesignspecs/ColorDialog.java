package com.androidessence.materialdesignspecs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private static final String ARG_COLOR_LIST = "colorList";

    private OnColorSelectedListener listener;

    public static ColorDialog newInstance(List<Integer> colorList) {
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_COLOR_LIST, new ArrayList<>(colorList));

        ColorDialog dialog = new ColorDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_color, container, false);

        List<Integer> colorList = new ArrayList<>();

        if (getArguments() != null && getArguments().containsKey(ARG_COLOR_LIST)) {
            colorList = getArguments().getIntegerArrayList(ARG_COLOR_LIST);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ColorAdapter adapter = new ColorAdapter(colorList);

        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
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
