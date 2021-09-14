package com.vdx.chiprv.items

abstract class ChipItem : Selection() {
}

abstract class Selection(
    var selected: Boolean = false,
    var selectedPosition: Int = -1
)