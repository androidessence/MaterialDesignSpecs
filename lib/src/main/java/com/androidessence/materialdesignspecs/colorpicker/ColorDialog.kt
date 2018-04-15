package com.androidessence.materialdesignspecs.colorpicker

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidessence.materialdesignspeclibrary.R

/**
 * Dialog fragment that displays a list of colors resources.
 *
 * @property[onColorSelectedListener] A callback to notify anytime a color is selected.
 * @property[layoutManager] The layout manager applied to the RecyclerView in the dialog. If the user
 * never supplies this, we'll use a GridLayoutManager.
 * @property[columnCount] The number of columns in the grid of colors. Used for the default [layoutManager].
 * @property[adapter] An adapter to display our list of color resources. Defaults to a [CircleColorAdapter] if not supplied.
 *
 * Created by adam.mcneilly on 5/8/17.
 */
@Suppress("MemberVisibilityCanBePrivate")
class ColorDialog : DialogFragment() {
    var onColorSelectedListener: OnColorSelectedListener? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var columnCount: Int = 0
    var adapter: BaseColorAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.dialog_color, container, false)

        val colorList = arguments?.getIntegerArrayList(ARG_COLOR_LIST) ?: ArrayList()
        val selectedPos = arguments?.getInt(ARG_SELECTED_COLOR_POSITION) ?: -1

        if (adapter == null) {
            adapter = CircleColorAdapter(colorSelectedListener, colorList, selectedPos)
        } else {
            adapter?.colors = colorList
            adapter?.selectedPosition = selectedPos
            adapter?.onColorSelectedListener = colorSelectedListener
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
         */
        fun onColorSelected(selectedPos: Int, color: Int?)
    }

    /**
     * Listener that this dialog uses, which redirects the callback to any [onColorSelectedListener] the user applied, and dismisses the dialog.
     *
     * We use this so that we can instantiate a listener right away, but proxy the calls back to [onColorSelectedListener],
     * which the user may have defined later.
     */
    private val colorSelectedListener = object : OnColorSelectedListener {

        override fun onColorSelected(selectedPos: Int, color: Int?) {
            onColorSelectedListener?.onColorSelected(selectedPos, color)

            dismiss()
        }
    }

    companion object {
        private const val ARG_COLOR_LIST = "colorList"
        private const val ARG_SELECTED_COLOR_POSITION = "colorPosition"

        /**
         * Creates an instance of the ColorDialog.
         *
         * @param[colorList] The color resourcese to display in the dialog.
         * @param[selectedPos] The position in the list to set as the initially selected color.
         */
        @JvmStatic
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
