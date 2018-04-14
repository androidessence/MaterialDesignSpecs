package com.androidessence.materialdesignspecs

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView

import com.androidessence.materialdesignspeclibrary.R

@Suppress("unused")
/**
 * Adapter that displays a bunch of circular color choices.
 *
 *
 * Created by ankitagrawal on 5/15/17.
 */
class CircleColorAdapter : BaseColorAdapter {

    constructor(listener: ColorDialog.OnColorSelectedListener) : super(listener)

    constructor(colors: List<Int>, listener: ColorDialog.OnColorSelectedListener) : super(listener, colors)

    constructor(colors: List<Int>, listener: ColorDialog.OnColorSelectedListener, selectedPos: Int) : super(listener, colors, selectedPos)

    override fun getRVItemLayout(): Int {
        return R.layout.circle_list_item_color
    }

    override fun getViewHolder(view: View): BaseColorAdapter.BaseColorViewHolder {
        return CircleColorViewHolder(view)
    }

    inner class CircleColorViewHolder(view: View) : BaseColorAdapter.BaseColorViewHolder(view) {
        private var colorView: View? = null
        private var imgCheckView: ImageView? = null

        override fun initView(view: View) {
            colorView = view
            imgCheckView = view.findViewById(R.id.img_check) as ImageView
            colorView?.setOnClickListener(this)
        }

        private fun createSelector(color: Int): Drawable {
            val coloredCircle = ShapeDrawable(OvalShape())
            coloredCircle.paint.color = color
            val darkerCircle = ShapeDrawable(OvalShape())
            darkerCircle.paint.color = shiftColor(color)

            val stateListDrawable = StateListDrawable()
            stateListDrawable.addState(intArrayOf(-android.R.attr.state_pressed), coloredCircle)
            stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), darkerCircle)
            return stateListDrawable
        }

        private fun shiftColor(color: Int): Int {
            val hsv = FloatArray(3)
            Color.colorToHSV(color, hsv)
            hsv[2] *= 0.9f // value component
            return Color.HSVToColor(hsv)
        }

        override fun bindColor(color: Int?) {
            val visibility = if (selectedPosition == adapterPosition) View.VISIBLE else View.GONE
            imgCheckView?.visibility = visibility

            val context = colorView?.context
            if (context != null && color != null) {
                val colorVal = ContextCompat.getColor(context, color)
                val selector = createSelector(colorVal)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val states = arrayOf(intArrayOf(-android.R.attr.state_pressed), intArrayOf(android.R.attr.state_pressed))
                    val colors = intArrayOf(shiftColor(colorVal), colorVal)
                    val rippleColors = ColorStateList(states, colors)
                    setBackgroundCompat(colorView!!, RippleDrawable(rippleColors, selector, null))
                } else {
                    setBackgroundCompat(colorView!!, selector)
                }
            }
        }

        @Suppress("DEPRECATION")
        private fun setBackgroundCompat(view: View, d: Drawable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.background = d
            } else {
                view.setBackgroundDrawable(d)
            }
        }

    }
}
