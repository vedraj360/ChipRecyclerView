package com.vdx.chiprv.customui

import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.amulyakhare.textdrawable.TextDrawable
import com.vdx.chiprv.R
import com.vdx.chiprv.customui.data.ChipTextData
import com.vdx.chiprv.utils.changeTextToInitials
import com.vdx.chiprv.utils.getUniqueColor
import com.vdx.chiprv.viewholder.ChipViewHolder
import com.vdx.chiprv.viewholder.ChipViewHolderClickEventListener
import de.hdodenhof.circleimageview.CircleImageView

class CircularTextChipViewHolder(
    private val itemView: View,
    clickEventListener: ChipViewHolderClickEventListener
) : ChipViewHolder<ChipTextData>(itemView, clickEventListener) {
    lateinit var nameTv: TextView
    lateinit var circleImageView: CircleImageView
    lateinit var dot: ImageView

    override fun bind(item: ChipTextData, position: Int, items: Int) {
        nameTv.text = item.name

        val drawable = TextDrawable.builder()
            .beginConfig()
            .useFont(Typeface.DEFAULT_BOLD)
            .height(112)
            .width(112)
            .endConfig()
            .buildRound(
                item.name.changeTextToInitials(),
                item.name.hashCode().getUniqueColor(item.name)
            )
        circleImageView.setImageDrawable(drawable)

        if (item.selected) {
            dot.visibility = View.VISIBLE
            circleImageView.borderWidth = 8
            circleImageView.borderColor =
                ContextCompat.getColor(circleImageView.context, R.color.red)
        } else {
            dot.visibility = View.GONE
            circleImageView.borderWidth = 2
            circleImageView.borderColor =
                ContextCompat.getColor(circleImageView.context, R.color.black)
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