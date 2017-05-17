package com.androidessence.materialdesignspecs

import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.androidessence.materialdesignspeclibrary.R

/**
 * Base adapter class for displaying colors in a RecyclerView.
 *
 * Created by adam.mcneilly on 5/17/17.
 */
abstract class BaseColorAdapter(var onColorSelectedListener: ColorDialog.OnColorSelectedListener?) : RecyclerView.Adapter<BaseColorAdapter.BaseColorViewHolder>() {

    var colors: List<Int> = ArrayList()
    var selectedPosition: Int = RecyclerView.NO_POSITION
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    constructor(listener: ColorDialog.OnColorSelectedListener, colors: List<Int>) : this(listener) {
        this.colors = colors
    }

    constructor(listener: ColorDialog.OnColorSelectedListener, colors: List<Int>, selectedPosition: Int) : this(listener, colors) {
        this.selectedPosition = selectedPosition
    }

    override fun onBindViewHolder(holder: BaseColorViewHolder?, position: Int) {
        holder?.bindColor(colors[position])
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseColorViewHolder {
        val context = parent?.context
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
     */
    abstract inner class BaseColorViewHolder(private val colorView: View) : RecyclerView.ViewHolder(colorView), View.OnClickListener {
        private val imgCheckView: ImageView

        init {
            initView(colorView)
            imgCheckView = colorView.findViewById(R.id.img_check) as ImageView
            colorView.setOnClickListener(this)
        }

        /**
         * Abstract method to provide any custom initializations the user may want.

         * @param view The itemView of this ViewHolder.
         */
        abstract fun initView(view: View)

        /**
         * Binds a color to this ViewHolder.

         * @param color The color to display.
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