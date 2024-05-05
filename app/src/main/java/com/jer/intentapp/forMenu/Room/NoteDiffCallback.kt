package com.jer.intentapp.forMenu.Room

import androidx.recyclerview.widget.DiffUtil

class NoteDiffCallback(private val oldListNote: List<NoteForRoom>, private val newListNote: List<NoteForRoom>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldListNote.size

    override fun getNewListSize(): Int = newListNote.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListNote[oldItemPosition].id == newListNote[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldListNote[oldItemPosition]
        val newItem = newListNote[newItemPosition]
        return oldItem.title == newItem.title && oldItem.description == newItem.description
    }
}