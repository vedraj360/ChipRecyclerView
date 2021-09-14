package com.vdx.chiprv.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vdx.chiprv.items.ChipItem
import com.vdx.chiprv.viewholder.ChipViewHolder
import com.vdx.chiprv.viewholder.ChipViewHolderBuilder
import com.vdx.chiprv.viewholder.ChipViewHolderClickEventListener


class ChipAdapter<T : ChipItem>() : RecyclerView.Adapter<ChipViewHolder<T>>() {
    private var isSelected: Boolean = false
    var ITEM_TYPE = 0

    private val rootItems: ArrayList<T> = ArrayList()
    private val items: ArrayList<T> = ArrayList()
    private var selectedPosition = NO_POSITION
    private var prevSelectedPosition = NO_POSITION


    private var context: Context? = null
    private var recyclerClickListener: ChipClickListener<T>? = null


    private val holders: HashMap<Int?, CHolderWrapper> = HashMap()
    private val viewTypes: HashMap<Class<out T?>, Int> = HashMap()

    fun context(context: Context?): ChipAdapter<T> {
        this.context = context
        return this
    }

    fun holder(
        itemClass: Class<out T?>,
        viewHolderClass: Class<out ChipViewHolder<out T>>?,
        resourceID: Int,
        spanCount: Int
    ): ChipAdapter<T> {
        val viewType = ITEM_TYPE++
        viewTypes[itemClass] = viewType
        holders[viewType] =
            CHolderWrapper().setHolderClass(viewHolderClass).setResourceID(resourceID)
                .setSpanCount(spanCount)
        return this
    }


    fun listener(recyclerClickListener: ChipClickListener<T>?): ChipAdapter<T> {
        this.recyclerClickListener = recyclerClickListener
        return this
    }

    fun set(items: ArrayList<T>) {
        this[items] = true
    }

    operator fun set(items: ArrayList<T>, overrideRootItems: Boolean) {
        if (overrideRootItems) {
            rootItems.clear()
            rootItems.addAll(items)
        }
        this.items.clear()
        this.items.addAll(items)
        notifyItemRangeInserted(0, items.size)
    }

    fun addAll(items: ArrayList<T>) {
        rootItems.addAll(items)
        this.items.addAll(items)
        notifyItemRangeInserted(this.items.size + 1, items.size)
    }

    fun add(item: T) {
        rootItems.add(item)
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun remove() {
        rootItems.clear()
        items.clear()
        notifyItemRangeRemoved(0, items.size)
    }

    fun remove(index: Int) {
        val item = items[index]
        rootItems.remove(item)
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun remove(item: T) {
        rootItems.remove(item)
        val index = items.indexOf(item)
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun update(item: T) {
        val index = items.indexOf(item)
        this.notifyItemChanged(index)
    }

    fun getItems(): ArrayList<T> {
        return items
    }

    fun getSpanCount(position: Int): Int {
        return holders[viewTypes[items[position].javaClass]]!!.spanCount
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChipViewHolder<T> {
        val cHolderWrapper = holders[i]
        return cHolderWrapper?.let {
            ChipViewHolderBuilder<ChipViewHolder<T>>().typeOf(cHolderWrapper.getHolderClass())
                .withContext(context).withLayout(
                    it.resourceID
                ).build(viewGroup, object : ChipViewHolderClickEventListener {
                    override fun onClickEvent(position: Int, clickEventCode: Int) {
                        if (recyclerClickListener != null) {
                            if (isSelected) {
                                onClick(position, clickEventCode)
                            }
                            recyclerClickListener?.onClick(
                                items[position],
                                position,
                                clickEventCode
                            )
                        }
                    }
                })
        }!!
    }

    override fun onBindViewHolder(GRViewHolder: ChipViewHolder<T>, i: Int) {
        GRViewHolder.bind(items[i], i)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return viewTypes[items[position].javaClass]!!
    }


    fun getRootItems(): ArrayList<T> {
        return rootItems
    }

    fun onClick(position: Int, clickEventCode: Int) {
        recyclerClickListener?.let { _ ->
            val isSelected = items[position].selected
            if (isSelected) {
                items[position].selected = false
                notifyItemChanged(position)
                selectedPosition = NO_POSITION
                prevSelectedPosition = NO_POSITION
            } else {
                items[position].selected = true
                selectedPosition = position
                if (prevSelectedPosition == NO_POSITION) {
                    prevSelectedPosition = selectedPosition
                } else {
                    items[prevSelectedPosition].selected = false
                    notifyItemChanged(prevSelectedPosition)
                    prevSelectedPosition = selectedPosition
                }
                if (selectedPosition >= 0) notifyItemChanged(selectedPosition)
            }
        }
    }

    fun setSelection(selected: Boolean) {
        this.isSelected = selected
    }

    fun moveToPosition(position: Int) {
        if (isSelected) {
            if (position != NO_POSITION) {
                if (!items.isNullOrEmpty() && position < items.size) {
                    selectedPosition = position
                    prevSelectedPosition = position
                    items[position].selected = true
                    notifyItemChanged(position)
                }
            }
        }
    }

    private class CHolderWrapper {
        private var holderClass: Class<out ChipViewHolder<*>>? = null
        var resourceID = 0
            private set
        var spanCount = 0
            private set

        fun getHolderClass(): Class<out ChipViewHolder<*>>? {
            return holderClass
        }

        fun setHolderClass(holderClass: Class<out ChipViewHolder<*>>?): CHolderWrapper {
            this.holderClass = holderClass
            return this
        }

        fun setResourceID(resourceID: Int): CHolderWrapper {
            this.resourceID = resourceID
            return this
        }

        fun setSpanCount(spanCount: Int): CHolderWrapper {
            this.spanCount = spanCount
            return this
        }
    }

    companion object {
        private const val NO_POSITION = RecyclerView.NO_POSITION
    }
}

interface ChipClickListener<T> {
    fun onClick(item: T, position: Int, clickEventCode: Int)
}