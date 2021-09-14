package com.vdx.chiprv.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vdx.chiprv.items.ChipItem


@Suppress("LeakingThis")
abstract class ChipViewHolder<T : ChipItem>(
    private val itemView: View,
    private val clickEventListener: ChipViewHolderClickEventListener
) : RecyclerView.ViewHolder(itemView) {
    init {
        init()
        assign()
    }

    abstract fun bind(item: T, position: Int)

    abstract fun init()

    abstract fun assign()

    protected open fun init(id: Int): View? {
        return itemView.findViewById(id)
    }

    protected open fun assignClickEvent(v: View, clickEventCode: Int) {
        v.setOnClickListener { view: View? ->
            clickEventListener.onClickEvent(
                adapterPosition,
                clickEventCode
            )
        }
    }

    protected open fun assignClickEvent(v: View) {
        assignClickEvent(v, v.id)
    }

    protected open fun assignLongClickEvent(v: View, clickEventCode: Int) {
        v.setOnLongClickListener { view: View? ->
            clickEventListener.onClickEvent(adapterPosition, clickEventCode)
            true
        }
    }

    protected open fun assignLongClickEvent(v: View) {
        assignLongClickEvent(v, v.id)
    }

    protected open fun triggerClickEvent(clickEventCode: Int) {
        clickEventListener.onClickEvent(adapterPosition, clickEventCode)
    }

    protected open fun triggerClickEvent(v: View) {
        clickEventListener.onClickEvent(adapterPosition, v.id)
    }
}

interface ChipViewHolderClickEventListener {
    fun onClickEvent(position: Int, clickEventCode: Int)
}