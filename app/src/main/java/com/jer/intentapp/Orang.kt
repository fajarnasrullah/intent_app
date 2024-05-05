package com.jer.intentapp

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Orang(
    val nama: String,
    val umur: Int,
    val profesi: String,
) : Parcelable

