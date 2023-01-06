package com.naedri.andro_potter.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemBucket(var quantity: Int = 1, val book: Book) : Parcelable {
    override fun toString(): String {
        return "${quantity} x ${book.title}"
    }
}