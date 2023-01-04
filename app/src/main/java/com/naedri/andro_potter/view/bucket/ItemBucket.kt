package com.naedri.andro_potter.view.bucket

import android.os.Parcelable
import com.naedri.andro_potter.model.Book
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemBucket(var quantity: Int = 1, val book: Book):Parcelable {
}