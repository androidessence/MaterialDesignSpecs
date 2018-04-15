package com.androidessence.materialdesignspecs.colorpicker

import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView

import com.androidessence.materialdesignspeclibrary.R

@Suppress("unused")
/**
 * Adapter that displays a bunch of square color choices.
 *
 * Created by ankitagrawal on 5/15/17.
 */
class SquareColorAdapter @JvmOverloads constructor(
        listener: ColorDialog.OnColorSelectedListener?,
        colors: List<Int> = ArrayList(),
        initialPosition: Int = RecyclerView.NO_POSITION
) : BaseColorAdapter(listener, colors, initialPosition) {

    override fun getViewHolder(view: View): BaseColorViewHolder {
        return SquareColorViewHolder(view)
    }

    @LayoutRes
    override fun getRVItemLayout(): Int {
        return R.layout.list_item_color
    }

    /**
     * ViewHolder for displaying a color resource as a square.
     *
     * TODO: Don't make this an inner class.
     */
    inner class SquareColorViewHolder(view: View) : BaseColorViewHolder(view) {
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
