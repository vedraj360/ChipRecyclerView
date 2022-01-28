package com.vdx.chiprecycler.viewholder

import android.view.View
import android.widget.TextView
import com.vdx.chiprecycler.R
import com.vdx.chiprecycler.model.Data
import com.vdx.chiprv.viewholder.ChipViewHolder
import com.vdx.chiprv.viewholder.ChipViewHolderClickEventListener

class DataViewHolder(
    itemView: View,
    clickEventListener: ChipViewHolderClickEventListener
) : ChipViewHolder<Data>(itemView, clickEventListener) {
    private lateinit var userNameTv: TextView

    override fun bind(item: Data, position: Int, items: Int) {
        userNameTv.text = item.userName;
    }

    override fun init() {
        userNameTv = init(R.id.user_name_tv) as TextView;
    }

    override fun assign() {
        assignClickEvent(userNameTv);
    }
}