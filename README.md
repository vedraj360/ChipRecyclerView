# ChipRecyclerView 🔥✅

[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
![Language](https://img.shields.io/badge/language-Kotlin-orange.svg)
[![Kotlin Version](https://img.shields.io/badge/Kotlin-v1.5.21-blue.svg)](https://kotlinlang.org)
[![](https://jitpack.io/v/vedraj360/ChipRecyclerView.svg)](https://jitpack.io/#vedraj360/ChipRecyclerView)

A simple library to **Create Circular chips like Instagram stories or a custom recyclerView without need of adapter.**.


# 🎬Preview

|  CircularChipViewHolder &  CircularTextChipViewHolder
|--|
| <img src="/gifs/demog.gif" width="300" height="500"/> |

# How it works:

1. Gradle Dependency

- Add the JitPack repository to your project's build.gradle file

```groovy
    allprojects {
        repositories {
    	    maven { url 'https://jitpack.io' }
        }
    }
```
- Add the dependency
```
dependencies {
	        implementation 'com.github.vedraj360:ChipRecyclerView:Release-1.0.5'
	}
```

### Adding to the layout
Add **ChipRV** to your layout.
```xml
   <com.vdx.chiprv.ChipRV
        android:id="@+id/RecyclerView2"
        android:layout_width="match_parent"
        android:layout_height="100dp" />
```
* There are multiple viewholders already present in library out of the box ✌.

  1 CircularChipViewHolder

  2 CircularTextChipViewHolder

Now, in your Activity or Fragment, you can simply initialize your **ChipRV**.

```kotlin
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

        recyclerView.addAll(chipLists()) // List of items.
```
**If you don't want to use the given view holders you can create your own custom view holder and data class and pass them to recyclerView.**

**See Documentation for more details. 💻**

## Documentation

### Creating custom view holder
Create a class which extends from **ChipViewHolder** depending on your item.
>  Don't forget to create **ChipItem** before creating **ChipViewHolder**.

* To create ChipItem just extends the model class with ChipItem.
```kotlin
data class ChipData(val name: String, val url: String) : ChipItem()
```
* Custom viewholder.

```kotlin
class CircularChipViewHolder(
    private val itemView: View,
    clickEventListener: ChipViewHolderClickEventListener
) : ChipViewHolder<ChipData>(itemView, clickEventListener) {
    private lateinit var nameTv: TextView
    private lateinit var circleImageView: CircleImageView
    private lateinit var dot: ImageView

    override fun bind(item: ChipData, position: Int) {
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

```


### Constructuring
##### init(Context context)
>returns ChipRV's itself.

##### listener(ChipClickListener<T> recyclerClickListener)
>returns ChipRV's itself.

##### layout()
>Use layout().linear()` and see the options below. After all call `addView()` to begin adding view holders.

###### layout().orientation()
| method | params | default | returns | description |
|:--|:--|:--|:--|:--|
|orientation()|RVOrientation|VERTICAL|itself|Sets the orientation of grid layout. `RVOrientation.VERTICAL`, `RVOrientation.HORIZONTAL`|
|reverse()|boolean|false|itself|Sets the reverse ordering.|
|span()|int|1|itself|Sets the total span count of one row.|
|addView()|-|-|ChipViewHolderBuilder|Starts to add view holders.|

Example
```kotlin

layout()
    .orientation(RVOrientation.VERTICAL) // optional
    .reverse(false) // optional
    .addView() // end
    
```

###### layout().linear()
| method | params | default | returns | description |
|:--|:--|:--|:--|:--|
|orientation()|RVOrientation|VERTICAL|itself|Sets the orientation of grid layout. `RVOrientation.VERTICAL`, `RVOrientation.HORIZONTAL`|
|reverse()|boolean|false|itself|Sets the reverse ordering.|
|addView()|-|-|ChipViewHolder|Starts to add view holders.|

Example
```kotlin

layout()
    .linear() // start
    .orientation(RVOrientation.VERTICAL) // optional
    .reverse(false) // optional
    .addView() // end
    
```

###### setSelection()

If setSelection is set true you can make view act as the single selection of item.
In viewholder's data item you get a **selected** boolean from you can identiy which item is selected.

```kotlin 
    // ChipData is is model class 
    override fun bind(item: ChipData, position: Int) {
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
```

##### addView()
| method | params | default | returns | description |
|:--|:--|:--|:--|:--|
|holder()|Class|REQUIRED|itself|Sets the class of holder which you created before.|
|item()|Class|REQUIRED|itself|Sets the class of item associated with your holder.|
|layout()|int|REQUIRED|itself|Sets the layout id of your view holder.|
|addView()|-|-|ChipViewHolderBuilder|Starts to add more view holders.|
|build()|-|-|GRView|Ends the build process.|

Example
```kotlin
addView() // start
.item(ChipTextData::class.java) // item class
.holder(CircularTextChipViewHolder::class.java) // holder class
.layout(R.layout.chip_li) // holder resource
.build() // finally build
```
### Items
| method | params | description |
|:--|:--|:--|
|set()|ArrayList&lt;T&gt;| Sets the items|
|addAll()|ArrayList&lt;T&gt;|Adds all items|
|add()|T|Adds the item|
|remove()|-|Clears all items|
|remove()|int|Removes the item at given index|
|remove()|T|Removes the item|
|update()|T|Updates the given item|
|getItems()|ArrayList&lt;T&gt;|Returns all items|

# Other Library used:
* __[TextDrawable](https://github.com/amulyakhare/TextDrawable)__
* __[Glide](https://github.com/bumptech/glide)__

## Find this library useful? :heart:
Support it by joining __[stargazers](https://github.com/vedraj360/ChipRecyclerView/stargazers)__ for this repository. :star:

## 🤝 How to Contribute

Whether you're helping us fix bugs, improve the docs, or a feature request, we'd love to have you! :muscle:

Check out our [**Contributing Guide**](https://github.com/vedraj360/ChipRecyclerView/blob/master/CONTRIBUTING.md) for ideas on contributing.

## Bugs and Feedback

For bugs, feature requests, and discussion please use [GitHub Issues](https://github.com/vedraj360/ChipRecyclerView/issues).

## License

```
MIT License

Copyright (c) 2021 vedraj360

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```