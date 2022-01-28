package com.vdx.chiprecycler.viewholder

import android.util.Log
import android.view.View
import android.widget.TextView
import com.vdx.chiprecycler.R
import com.vdx.chiprecycler.model.User
import com.vdx.chiprv.viewholder.ChipViewHolder
import com.vdx.chiprv.viewholder.ChipViewHolderClickEventListener

class UserViewHolder(
    itemView: View,
    clickEventListener: ChipViewHolderClickEventListener
) : ChipViewHolder<User>(itemView, clickEventListener) {
    private lateinit var userNameTv: TextView

    override fun bind(item: User, position: Int, items: Int) {
        val text = "${item.userName}  ${item.selected}"
        userNameTv.text = text;
        if (item.selected) {
            Log.e("TAG", "bind: item is selected ${item.selected}")
            Log.e("TAG", "bind: item current position ${item.selectedPosition}")
        } else {
            Log.e("TAG", "bind: item is not selected ${item.selected}")
            Log.e("TAG", "bind: item position ${item.selectedPosition}")

        }
    }

    override fun init() {
        userNameTv = init(R.id.user_name_tv) as TextView;
    }

    override fun assign() {
        assignClickEvent(userNameTv);
    }
}

