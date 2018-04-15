package com.androidessence.materialdesignspecs.colorpicker

import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.androidessence.materialdesignspeclibrary.R

/**
 * Base adapter class for displaying a list of colors in a RecyclerView.
 *
 * @param[onColorSelectedListener] A callback to notify when a color is selected in the dialog.
 * @param[colors] The list of color resources to display in the adapter.
 * @param[initialPosition] The position in the list of [colors] to set as the initially selected one.
 * @property[selectedPosition] The position in the list of [colors] that the user has selected, and
 * is used to notify the [onColorSelectedListener].
 *
 * Created by adam.mcneilly on 5/17/17.
 */
abstract class BaseColorAdapter @JvmOverloads constructor(
        var onColorSelectedListener: ColorDialog.OnColorSelectedListener?,
        var colors: List<Int> = ArrayList(),
        initialPosition: Int = RecyclerView.NO_POSITION
) : RecyclerView.Adapter<BaseColorAdapter.BaseColorViewHolder>() {

    var selectedPosition: Int = initialPosition
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Binds an individual color resource to the ViewHolder.
     */
    override fun onBindViewHolder(holder: BaseColorViewHolder, position: Int) {
        holder.bindColor(colors[position])
    }

    /**
     * Determines the number of items to display in the adapter.
     */
    override fun getItemCount(): Int {
        return colors.size
    }

    /**
     * Creates a ViewHolder for displaying a color resource.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseColorViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(getRVItemLayout(), parent, false)
        return getViewHolder(view)
    }

    /**
     * Abstract method that retrieves the item layout for a row in the RecyclerView.
     */
    @LayoutRes
    abstract fun getRVItemLayout(): Int

    /**
     * Abstract method for the ViewHolder used by this adapter.
     */
    abstract fun getViewHolder(view: View): BaseColorViewHolder

    /**
     * Base ViewHolder for a color adapter.
     *
     * TODO: Don't make this an inner class.
     */
    @Suppress("LeakingThis") //TODO: Can we avoid this?
    abstract inner class BaseColorViewHolder(private val colorView: View) : RecyclerView.ViewHolder(colorView), View.OnClickListener {
        private val imgCheckView: ImageView

        init {
            initView(colorView)
            imgCheckView = colorView.findViewById(R.id.img_check) as ImageView
            colorView.setOnClickListener(this)
        }

        /**
         * Abstract method to provide any custom initializations the user may want.
         */
        abstract fun initView(view: View)

        /**
         * Binds a color resource to this ViewHolder.
         */
        open fun bindColor(color: Int?) {
            val colorRes = ContextCompat.getColor(colorView.context, color!!)
            val visibility = if (selectedPosition == adapterPosition) View.VISIBLE else View.GONE

            this.colorView.setBackgroundColor(colorRes)
            this.imgCheckView.visibility = visibility
        }

        override fun onClick(v: View) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val color = colors[adapterPosition]
                onColorSelectedListener?.onColorSelected(adapterPosition, color)
            }
        }
    }
}