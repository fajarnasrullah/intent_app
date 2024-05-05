package com.jer.intentapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    var name: String,
    val description: String,
    val photoUrl: String,
): Parcelable
