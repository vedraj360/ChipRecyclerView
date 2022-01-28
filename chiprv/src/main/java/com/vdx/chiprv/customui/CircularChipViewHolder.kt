package com.vdx.chiprv.customui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.vdx.chiprv.R
import com.vdx.chiprv.customui.data.ChipData
import com.vdx.chiprv.viewholder.ChipViewHolder
import com.vdx.chiprv.viewholder.ChipViewHolderClickEventListener
import de.hdodenhof.circleimageview.CircleImageView

class CircularChipViewHolder(
    private val itemView: View,
    clickEventListener: ChipViewHolderClickEventListener
) : ChipViewHolder<ChipData>(itemView, clickEventListener) {
    private lateinit var nameTv: TextView
    private lateinit var circleImageView: CircleImageView
    private lateinit var dot: ImageView

    override fun bind(item: ChipData, position: Int, items: Int) {
        nameTv.text = item.name
        Glide.with(itemView.context).load(item.url)
            .into(circleImageView)
        if (item.selected) {
            dot.visibility = View.VISIBLE
            circleImageView.borderWidth = 8
            circleImageView.borderColor = ContextCompat.getColor(circleImageView.context, R.color.red)
        } else {
            dot.visibility = View.GONE
            circleImageView.borderWidth = 2
            circleImageView.borderColor = ContextCompat.getColor(circleImageView.context, R.color.black)
        }
    }

    override fun init() {
        nameTv = init(R.id.name) as TextView
        circleImageView = init(R.id.circleImageView) as CircleImageView
        dot = init(R.id.dot) as ImageView
    }

    override fun assign() {
        assignClickEvent(circleImageView)
    }
}