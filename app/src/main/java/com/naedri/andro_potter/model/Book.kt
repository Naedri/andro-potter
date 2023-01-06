package com.naedri.andro_potter.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// https://developer.android.com/kotlin/parcelize
@Parcelize
data class Book(
    val isbn: String,
    val title: String,
    val price: Int,
    val cover: String,
    val synopsis: List<String>
) : Parcelable
