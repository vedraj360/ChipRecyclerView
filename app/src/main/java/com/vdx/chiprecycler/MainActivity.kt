package com.vdx.chiprecycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vdx.chiprv.ChipRV
import com.vdx.chiprv.adapters.ChipAdapter
import com.vdx.chiprv.adapters.ChipClickListener
import com.vdx.chiprv.customui.CircularChipViewHolder
import com.vdx.chiprv.customui.CircularTextChipViewHolder
import com.vdx.chiprv.customui.data.ChipData
import com.vdx.chiprv.customui.data.ChipTextData
import com.vdx.chiprv.items.ChipItem
import com.vdx.chiprv.utils.RVOrientation


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpChipWithImageRv()
        setUpChipWithTextRv()
        setUpChipWithImageRvVertical()
        setUpChipWithTextRvVertical()
    }

    private fun setUpChipWithImageRv() {
        val recyclerView: ChipRV<ChipItem> = findViewById(R.id.RecyclerView)
        recyclerView.init(this)
            .listener(object : ChipClickListener<ChipItem> {
                override fun onClick(item: ChipItem, position: Int, clickEventCode: Int) {
                    if (item is ChipData) {
                        Toast.makeText(
                            this@MainActivity,
                            item.name,
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            })
            .setSelection(true)
            .layout().linear().orientation(RVOrientation.HORIZONTAL)
            .addView().item(ChipData::class.java).holder(CircularChipViewHolder::class.java)
            .layout(R.layout.chip_li)
            .build()

        recyclerView.addAll(chipLists())
        moveToPosition((2 until 10).random(), recyclerView)
    }

    private fun setUpChipWithImageRvVertical() {
        val recyclerView: ChipRV<ChipItem> = findViewById(R.id.RecyclerView3)
        recyclerView.init(this)
            .listener(object : ChipClickListener<ChipItem> {
                override fun onClick(item: ChipItem, position: Int, clickEventCode: Int) {
                    if (item is ChipData) {
                        Toast.makeText(
                            this@MainActivity,
                            item.name,
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            })
            .setSelection(true)
            .layout().linear().orientation(RVOrientation.VERTICAL)
            .addView().item(ChipData::class.java).holder(CircularChipViewHolder::class.java)
            .layout(R.layout.chip_li)
            .build()

        recyclerView.addAll(chipLists())
        moveToPosition((2 until 10).random(), recyclerView)
    }


    private fun setUpChipWithTextRv() {
        val recyclerView: ChipRV<ChipItem> = findViewById(R.id.RecyclerView2)
        recyclerView.init(this)
            .listener(object : ChipClickListener<ChipItem> {
                override fun onClick(item: ChipItem, position: Int, clickEventCode: Int) {
                    if (item is ChipTextData) {
                        Toast.makeText(
                            this@MainActivity,
                            item.name,
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }

            })
            .setSelection(true)
            .layout().linear().orientation(RVOrientation.HORIZONTAL)
            .addView().item(ChipTextData::class.java).holder(CircularTextChipViewHolder::class.java)
            .layout(R.layout.chip_li)
            .build()

        recyclerView.addAll(chipTextLists())

    }

    private fun setUpChipWithTextRvVertical() {
        val recyclerView: ChipRV<ChipItem> = findViewById(R.id.RecyclerView4)
        recyclerView.init(this)
            .listener(object : ChipClickListener<ChipItem> {
                override fun onClick(item: ChipItem, position: Int, clickEventCode: Int) {
                    if (item is ChipTextData) {
                        Toast.makeText(
                            this@MainActivity,
                            item.name,
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }

            })
            .setSelection(true)
            .layout().linear().orientation(RVOrientation.VERTICAL)
            .addView().item(ChipTextData::class.java).holder(CircularTextChipViewHolder::class.java)
            .layout(R.layout.chip_li)
            .build()

        recyclerView.addAll(chipTextLists())

    }


    private fun moveToPosition(position: Int, recyclerView: ChipRV<ChipItem>) {
        if (recyclerView.adapter != null) {
            val adapter = recyclerView.getChipAdapter as ChipAdapter
            val linearLayoutManager: LinearLayoutManager =
                recyclerView.layoutManager as LinearLayoutManager
            linearLayoutManager.scrollToPosition(position)
            adapter.moveToPosition(position)
        }
    }


    private fun chipLists(): ArrayList<ChipItem> {
        val list: ArrayList<ChipData> = ArrayList()

        var chipData =
            ChipData("Michael", "https://reqres.in/img/faces/7-image.jpg")
        list.add(chipData)

        chipData = ChipData("Lindsay", "https://reqres.in/img/faces/8-image.jpg")
        list.add(chipData)

        chipData = ChipData("Tobias", "https://reqres.in/img/faces/9-image.jpg")
        list.add(chipData)


        chipData = ChipData("Byron", "https://reqres.in/img/faces/10-image.jpg")
        list.add(chipData)


        chipData = ChipData("George", "https://reqres.in/img/faces/11-image.jpg")
        list.add(chipData)

        chipData = ChipData("Rachel", "https://reqres.in/img/faces/12-image.jpg")
        list.add(chipData)

        chipData = ChipData("Byron", "https://reqres.in/img/faces/1-image.jpg")
        list.add(chipData)


        chipData = ChipData("Trixy", "https://reqres.in/img/faces/2-image.jpg")
        list.add(chipData)


        chipData = ChipData("Brad", "https://reqres.in/img/faces/3-image.jpg")
        list.add(chipData)

        chipData = ChipData("Janet", "https://reqres.in/img/faces/4-image.jpg")
        list.add(chipData)

        chipData = ChipData("Charles", "https://reqres.in/img/faces/5-image.jpg")
        list.add(chipData)

        chipData = ChipData("Tracey", "https://reqres.in/img/faces/6-image.jpg")
        list.add(chipData)

        return list as ArrayList<ChipItem>
    }

    private fun chipTextLists(): ArrayList<ChipItem> {
        val list: ArrayList<ChipTextData> = ArrayList()
        var chipTextData =
            ChipTextData("Michael")
        list.add(chipTextData)

        chipTextData = ChipTextData("Lindsay")
        list.add(chipTextData)

        chipTextData = ChipTextData("Tobias")
        list.add(chipTextData)


        chipTextData = ChipTextData("Byron")
        list.add(chipTextData)


        chipTextData = ChipTextData("George")
        list.add(chipTextData)

        chipTextData = ChipTextData("Rachel")
        list.add(chipTextData)

        chipTextData = ChipTextData("Byron")
        list.add(chipTextData)


        chipTextData = ChipTextData("Trixy")
        list.add(chipTextData)


        chipTextData = ChipTextData("Brad")
        list.add(chipTextData)

        chipTextData = ChipTextData("Janet")
        list.add(chipTextData)

        chipTextData = ChipTextData("Charles")
        list.add(chipTextData)

        chipTextData = ChipTextData("Tracey")
        list.add(chipTextData)

        return list as ArrayList<ChipItem>
    }
}

