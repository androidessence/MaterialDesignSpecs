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
 * <p/>
 * Created by adam.mcneilly on 5/8/17.
 */
public class ColorDialog extends DialogFragment {

    /**
     * Argument key for the list of colors to display.
     */
    private static final String ARG_COLOR_LIST = "colorList";

    /**
     * Argument key for the position of the selected color.
     */
    private static final String ARG_SELECTED_COLOR_POSITION = "colorPosition";

    /**
     * The callback for when a color is selected.
     */
    private OnColorSelectedListener listener;

    /**
     * The LayoutManager for the RecyclerView.
     */
    private RecyclerView.LayoutManager layoutManager;

    /**
     * The number of columns in the grid.
     */
    private int columnCount;

    /**
     * The adapter to display the colors with.
     */
    private BaseColorAdapter adapter;

    /**
     * Creates an instance of the ColorDialog.
     * @param colorList The list of colors to display.
     * @param selectedPos The selected position for the color.
     * @return A ColorDialog the user can display.
     */
    public static ColorDialog newInstance(List<Integer> colorList, int selectedPos) {
        Bundle args = new Bundle();

        args.putIntegerArrayList(ARG_COLOR_LIST, new ArrayList<>(colorList));
        args.putInt(ARG_SELECTED_COLOR_POSITION, selectedPos);

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

        if (adapter == null) {
            adapter = new CircleColorAdapter(colorList, colorSelectedListener, selectedPos);
        } else {
            adapter.setColors(colorList);
            adapter.setSelectedPosition(selectedPos);
            adapter.setColorSelectedListener(colorSelectedListener);
        }

        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView != null) {
            if (layoutManager == null) {
                layoutManager = new GridLayoutManager(getContext(), columnCount);
            }

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    /**
     * Sets the callback to use when a color is selected.
     */
    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        this.listener = listener;
    }

    /**
     * Sets the LayoutManager for this RecyclerView to use.
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    /**
     * Sets the number of columns to use.
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * Sets the adapter for the RecyclerView.
     */
    public void setAdapter(BaseColorAdapter colorAdapter) {
        adapter = colorAdapter;
    }

    /**
     * Interface that handles a color being selected.
     */
    public interface OnColorSelectedListener {
        /**
         * Called when a color is selected in the dialog.
         * @param selectedPos The selected position of the color.
         * @param color The color resource that was selected.
         */
        void onColorSelected(int selectedPos, Integer color);
    }

    /**
     * Listener that this dialog uses, which redirects the callback to any listener the user applied.
     */
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
