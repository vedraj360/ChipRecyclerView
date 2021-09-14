package com.vdx.chiprv


import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vdx.chiprv.adapters.ChipAdapter
import com.vdx.chiprv.adapters.ChipClickListener
import com.vdx.chiprv.items.ChipItem
import com.vdx.chiprv.utils.RVOrientation
import com.vdx.chiprv.viewholder.ChipViewHolder


class ChipRV<T : ChipItem> : RecyclerView {
    private val orientation: RVOrientation = RVOrientation.VERTICAL
    private val reverseLayout = false
    private val spanCount = 1
    private var chipAdapter: ChipAdapter<T>? = null

    constructor(context: Context) : super(context) {}
    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, @Nullable attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    val getChipAdapter: ChipAdapter<T>?
        get() = chipAdapter

    fun set(items: ArrayList<T>?) {
        items?.let { chipAdapter?.set(it) }
    }

    fun addAll(items: ArrayList<T>) {
        items.let { }
        chipAdapter?.addAll(items)
    }

    fun add(item: T) {
        chipAdapter?.add(item)
    }

    fun remove() {
        chipAdapter?.remove()
    }

    fun remove(index: Int) {
        chipAdapter?.remove(index)
    }

    fun remove(item: T) {
        chipAdapter?.remove(item)
    }

    fun update(item: T) {
        chipAdapter?.update(item)
    }

    val items: ArrayList<T>
        get() = chipAdapter?.getItems()!!

    /**
     * BUILDER
     */
    fun init(context: Context?): ChipRV<T> {
        chipAdapter = ChipAdapter()
        chipAdapter?.context(context)
        return this
    }

    fun layout(): GRLayoutBuilder {
        return GRLayoutBuilder()
    }

    fun listener(recyclerClickListener: ChipClickListener<T>): ChipRV<T> {
        chipAdapter?.listener(recyclerClickListener)
        return this
    }


    fun setSelection(isSelected: Boolean): ChipRV<T> {
        chipAdapter?.setSelection(isSelected)
        return this
    }

    fun moveToPosition(position: Int) {
        chipAdapter?.moveToPosition(position)
    }

    open inner class GRLayoutBuilder {
        fun linear(): GRLinearLayoutBuilder {
            return GRLinearLayoutBuilder()
        }
    }

    inner class GRLinearLayoutBuilder : GRLayoutBuilder() {
        private var orientation: RVOrientation = RVOrientation.VERTICAL
        private var reverse = false
        fun orientation(orientation: RVOrientation): GRLinearLayoutBuilder {
            this.orientation = orientation
            return this
        }

        fun reverse(reverse: Boolean): GRLinearLayoutBuilder {
            this.reverse = reverse
            return this
        }

        fun addView(): GRViewHolderBuilder {
            when (orientation) {
                RVOrientation.VERTICAL -> setLayoutManager(
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL,
                        reverse
                    )
                )
                RVOrientation.HORIZONTAL -> setLayoutManager(
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        reverse
                    )
                )
            }
            return GRViewHolderBuilder()
        }
    }


    inner class GRViewHolderBuilder {
        var itemClass: Class<out T>? = null
        var viewHolderClass: Class<out ChipViewHolder<out T>?>? = null
        var resourceID = 0
        var spanCount = 1
        fun item(itemClass: Class<out T>?): GRViewHolderBuilder {
            this.itemClass = itemClass
            return this
        }

        fun holder(viewHolderClass: Class<out ChipViewHolder<out T>?>): GRViewHolderBuilder {
            this.viewHolderClass = viewHolderClass
            return this
        }

        fun layout(resourceID: Int): GRViewHolderBuilder {
            this.resourceID = resourceID
            return this
        }

        fun span(spanCount: Int): GRViewHolderBuilder {
            this.spanCount = spanCount
            return this
        }

        fun addView(): GRViewHolderBuilder {
            itemClass?.let { chipAdapter?.holder(it, viewHolderClass, resourceID, spanCount) }
            return GRViewHolderBuilder()
        }


        fun build(): ChipRV<T> {
            itemClass?.let { chipAdapter?.holder(it, viewHolderClass, resourceID, spanCount) }
            adapter = chipAdapter
            return this@ChipRV
        }
    }

    companion object {
        const val TAG = "ChipRV"
    }
}