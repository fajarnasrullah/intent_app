package com.jer.intentapp.forMenu.Room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class NoteForRoom (

    @PrimaryKey(true)
    @ColumnInfo("id")
    var id: Int = 0,

    @ColumnInfo("title")
    var title: String? = null,

    @ColumnInfo("description")
    var description: String? = null,

    @ColumnInfo("date")
    var date: String? = null

): Parcelable