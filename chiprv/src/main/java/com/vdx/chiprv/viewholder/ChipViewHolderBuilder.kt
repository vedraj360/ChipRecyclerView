package com.vdx.chiprv.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import java.lang.reflect.InvocationTargetException


class ChipViewHolderBuilder<T> {
    private var aClass: Class<*>? = null
    private var content: Context? = null
    private var layoutID = 0
    fun typeOf(aClass: Class<*>?): ChipViewHolderBuilder<T> {
        this.aClass = aClass
        return this
    }

    fun withContext(context: Context?): ChipViewHolderBuilder<T> {
        content = context
        return this
    }

    fun withLayout(layoutID: Int): ChipViewHolderBuilder<T> {
        this.layoutID = layoutID
        return this
    }

    fun build(root: ViewGroup?, clickEventListener: ChipViewHolderClickEventListener): T? {
        val view: View = LayoutInflater.from(content).inflate(layoutID, root, false)
        return try {
            aClass!!.getDeclaredConstructor(
                View::class.java,
                ChipViewHolderClickEventListener::class.java
            ).newInstance(view, clickEventListener) as T
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            null
        } catch (e: InstantiationException) {
            e.printStackTrace()
            null
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
            null
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
            null
        }
    }
}