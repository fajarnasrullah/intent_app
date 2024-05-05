package com.jer.intentapp.forMenu.SQLite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jer.intentapp.R
import com.jer.intentapp.databinding.ItemNoteBinding

class NoteAdapter(private val onItemClickCallBack: OnItemClickCallBack): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNote = ArrayList<Note>()
        set(listnotes) {
            if (listnotes.size > 0) {
                this.listNote.clear()
            }
            this.listNote.addAll(listnotes)
        }


    fun addItem(note: Note) {
        this.listNote.add(note)
        notifyItemInserted(this.listNote.size - 1)
    }

    fun updateItem(position: Int, note: Note) {
        this.listNote[position] = note
        notifyItemChanged(position, note)
    }

    fun removeItem(position: Int) {
        this.listNote.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listNote.size)
    }

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemNoteBinding.bind(itemView)
        fun bind(note: Note) {
            binding.tvItemTitle.text = note.title
            binding.tvItemDescription.text = note.description
            binding.tvItemDate.text = note.date
            binding.cvItemNote.setOnClickListener {
                onItemClickCallBack.onItemClicked(note, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)

    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        holder.bind(listNote[position])
    }

    override fun getItemCount(): Int = this.listNote.size



    interface OnItemClickCallBack {
        fun onItemClicked(selected: Note?, position: Int?)
    }
}