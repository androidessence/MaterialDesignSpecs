package com.androidessence.materialdesignspecs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidessence.materialdesignspeclibrary.R

/**
 * Dialog fragment that displays a list of colors.
 *
 *
 * Created by adam.mcneilly on 5/8/17.
 */
class ColorDialog : DialogFragment() {

    /**
     * The callback for when a color is selected.
     */
    var onColorSelectedListener: OnColorSelectedListener? = null

    /**
     * The LayoutManager for the RecyclerView.
     */
    var layoutManager: RecyclerView.LayoutManager? = null

    /**
     * The number of columns in the grid.
     */
    var columnCount: Int = 0

    /**
     * The adapter to display the colors with.
     */
    var adapter: BaseColorAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater?.inflate(R.layout.dialog_color, container, false)

        val colorList = arguments?.getIntegerArrayList(ARG_COLOR_LIST) ?: ArrayList()
        val selectedPos = arguments?.getInt(ARG_SELECTED_COLOR_POSITION) ?: -1

        if (adapter == null) {
            adapter = CircleColorAdapter(colorList, colorSelectedListener, selectedPos)
        } else {
            adapter?.setColors(colorList)
            adapter?.selectedPosition = selectedPos
            adapter?.setColorSelectedListener(colorSelectedListener)
        }

        if (layoutManager == null) {
            layoutManager = GridLayoutManager(context, columnCount)
        }

        val recyclerView = view as? RecyclerView
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

        return view
    }

    /**
     * Interface that handles a color being selected.
     */
    interface OnColorSelectedListener {
        /**
         * Called when a color is selected in the dialog.
         * @param selectedPos The selected position of the color.
         * *
         * @param color The color resource that was selected.
         */
        fun onColorSelected(selectedPos: Int, color: Int?)
    }

    /**
     * Listener that this dialog uses, which redirects the callback to any onColorSelectedListener the user applied.
     */
    private val colorSelectedListener = object : OnColorSelectedListener {

        override fun onColorSelected(selectedPos: Int, color: Int?) {
            onColorSelectedListener?.onColorSelected(selectedPos, color)

            dismiss()
        }
    }

    companion object {

        /**
         * Argument key for the list of colors to display.
         */
        private val ARG_COLOR_LIST = "colorList"

        /**
         * Argument key for the position of the selected color.
         */
        private val ARG_SELECTED_COLOR_POSITION = "colorPosition"

        /**
         * Creates an instance of the ColorDialog.
         * @param colorList The list of colors to display.
         * *
         * @param selectedPos The selected position for the color.
         * *
         * @return A ColorDialog the user can display.
         */
        fun newInstance(colorList: List<Int>, selectedPos: Int): ColorDialog {
            val args = Bundle()

            args.putIntegerArrayList(ARG_COLOR_LIST, ArrayList(colorList))
            args.putInt(ARG_SELECTED_COLOR_POSITION, selectedPos)

            val dialog = ColorDialog()
            dialog.arguments = args

            return dialog
        }
    }

}
