package com.jer.intentapp.forMenu.Room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jer.intentapp.R
import com.jer.intentapp.databinding.ActivityNoteRoomBinding

class NoteRoomActivity : AppCompatActivity() {


    private var _binding: ActivityNoteRoomBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: NoteRoomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteRoomBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val viewModel = obtainViewModel(this@NoteRoomActivity)
        viewModel.getAllNotes().observe(this) { noteList ->
            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        }


        adapter = NoteRoomAdapter()
        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter


        binding?.fabAdd?.setOnClickListener {
            val intent = Intent(this@NoteRoomActivity, NoteRoomAddUpdateActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): VMNoteRoom {
        val factory = VMFactoryRoom.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(VMNoteRoom::class.java)
    }




}