package com.vdx.chiprv.customui.data

import com.vdx.chiprv.items.ChipItem

data class ChipData(val name: String, val url: String) : ChipItem()

data class ChipTextData(val name: String) : ChipItem()