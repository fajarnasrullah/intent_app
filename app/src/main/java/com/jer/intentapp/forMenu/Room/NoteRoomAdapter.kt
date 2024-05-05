package com.jer.intentapp.forMenu.Room

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jer.intentapp.databinding.ItemNoteBinding

class NoteRoomAdapter: RecyclerView.Adapter<NoteRoomAdapter.NoteRoomViewHolder>() {

    inner class NoteRoomViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(note: NoteForRoom) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, NoteRoomAddUpdateActivity::class.java)
                    intent.putExtra(NoteRoomAddUpdateActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }

    }

    private val listNotes = ArrayList<NoteForRoom>()
    fun setListNotes(listNotes: List<NoteForRoom>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRoomViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return NoteRoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteRoomViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }


    override fun getItemCount(): Int {
        return listNotes.size
    }

}