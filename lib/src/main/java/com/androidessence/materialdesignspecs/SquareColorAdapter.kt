package com.androidessence.materialdesignspecs

import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView

import com.androidessence.materialdesignspeclibrary.R

@Suppress("unused")
/**
 * Adapter that displays a bunch of square color choices.
 *
 *
 * Created by ankitagrawal on 5/15/17.
 */
class SquareColorAdapter : BaseColorAdapter {

    constructor(listener: ColorDialog.OnColorSelectedListener) : super(listener)

    constructor(colors: List<Int>, listener: ColorDialog.OnColorSelectedListener) : super(listener, colors)

    constructor(colors: List<Int>, listener: ColorDialog.OnColorSelectedListener, selectedPos: Int) : super(listener, colors, selectedPos)

    override fun getViewHolder(view: View): BaseColorAdapter.BaseColorViewHolder {
        return SquareColorViewHolder(view)
    }

    @LayoutRes
    override fun getRVItemLayout(): Int {
        return R.layout.list_item_color
    }

    inner class SquareColorViewHolder(view: View) : BaseColorAdapter.BaseColorViewHolder(view) {
        private var colorView: View? = null
        private var imgCheckView: ImageView? = null

        override fun initView(view: View) {
            colorView = view
            imgCheckView = view.findViewById(R.id.img_check) as ImageView
            colorView?.setOnClickListener(this)
        }


        override fun bindColor(color: Int?) {
            val visibility = if (selectedPosition == adapterPosition) View.VISIBLE else View.GONE
            this.imgCheckView?.visibility = visibility

            val context = colorView?.context
            if (context != null && color != null) {
                val colorRes = ContextCompat.getColor(context, color)
                this.colorView?.setBackgroundColor(colorRes)
            }
        }
    }
}
