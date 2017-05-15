package com.androidessence.materialdesignspecs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
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

    private static final String ARG_SELECTED_COLOR_POSITION = "color_position";

    private OnColorSelectedListener listener;

    private RecyclerView.LayoutManager layoutManager;

    private int gridRowCount = 4;

    private BaseColorAdapter adapter;

    public static ColorDialog newInstance(List<Integer> colorList, int selectedPos) {
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_COLOR_LIST, new ArrayList<>(colorList));
        args.putInt(ARG_SELECTED_COLOR_POSITION,selectedPos);
        ColorDialog dialog = new ColorDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_color, container, false);

        List<Integer> colorList = new ArrayList<>();
        int selectedPos = -1;
        if (getArguments() != null && getArguments().containsKey(ARG_COLOR_LIST)) {
            colorList = getArguments().getIntegerArrayList(ARG_COLOR_LIST);
            selectedPos = getArguments().getInt(ARG_SELECTED_COLOR_POSITION, -1);
        }

         if(adapter==null) {
             adapter = new CircleColorAdapter(colorList, colorSelectedListener,selectedPos);
         }else{
             adapter.setColors(colorList);
             adapter.setSelected(selectedPos);
             adapter.setColorSelectedListener(colorSelectedListener);
         }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView != null) {
            if(layoutManager==null){
                layoutManager = new GridLayoutManager(getContext(),gridRowCount);
            }
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        this.listener = listener;
    }
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager=layoutManager;
    }
    public void setGridRowCount(int gridRowCount) {
        this.gridRowCount=gridRowCount;
    }
    public void setAdapter(BaseColorAdapter colorAdapter) {
        adapter = colorAdapter;
    }


    public interface OnColorSelectedListener {
        void onColorSelected(int selectedPos , Integer color);
    }
private OnColorSelectedListener colorSelectedListener = new OnColorSelectedListener() {

    @Override
    public void onColorSelected(int selectedPos, Integer color) {
        if (listener != null) {
            listener.onColorSelected(selectedPos, color);
        }
        dismiss();
    }
};

}
