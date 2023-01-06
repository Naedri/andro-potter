package com.naedri.andro_potter.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Bucket() : Parcelable {
    private var items: ArrayList<ItemBucket> = ArrayList()

    constructor(items: ArrayList<ItemBucket>) : this() {
        this.items = items
    }

    fun getItems(): ArrayList<ItemBucket> {
        return items;
    }

    override fun toString(): String {
        if (items.size > 0) {
            var s = "Bucket contains \n"
            items.map { itemBucket -> s += "- $itemBucket\n" }
            return s
        }
        return "Bucket is empty."
    }

    fun addItemBucket(book: Book) {
        var found = false
        items.forEach {
            if (it.book.isbn == book.isbn) {
                it.quantity = it.quantity + 1
                found = true
            }
        }
        if (!found) items.add(ItemBucket(1, book))
    }

    fun removeItemBucket(book: Book) {
        items.forEach {
            if (it.quantity > 1) {
                it.quantity = it.quantity - 1
            } else items.remove(it)
        }
    }

    fun emptyBucket() {
        items.clear()
    }

    fun countItemBucket(): Int {
        var acc = 0
        items.forEach { acc += it.quantity }
        return acc;
    }

    fun getBucketPrice(): Int {
        var acc = 0
        items.map { itemBucket -> acc += itemBucket.quantity * itemBucket.book.price }
        return acc;
    }
}