package com.jer.intentapp.forMenu.SQLite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jer.intentapp.R
import com.jer.intentapp.databinding.ActivityJustFormBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class JustFormActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    private lateinit var binding: ActivityJustFormBinding
    private lateinit var adapter: NoteAdapter

    val resultLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        result ->
        if (result.data != null) {
            when (result.resultCode) {
                NoteAddUpdateActivity.RESULT_ADD -> {
                    val note = result.data?.getParcelableExtra<Note>(NoteAddUpdateActivity.EXTRA_NOTE) as Note
                    adapter.addItem(note)
                    binding.rvNotes.smoothScrollToPosition(adapter.itemCount - 1)
                    snackBarMessage("Satu Item Berhasil Ditambahkan")
                }

                NoteAddUpdateActivity.RESULT_UPDATE -> {
                    val note = result.data?.getParcelableExtra<Note>(NoteAddUpdateActivity.EXTRA_NOTE) as Note
                    val position = result.data?.getIntExtra(NoteAddUpdateActivity.EXTRA_POSITION, 0) as Int
                    adapter.updateItem(position, note)
                    binding.rvNotes.smoothScrollToPosition(position)
                    snackBarMessage("Satu Item Berhasil Diubah")
                }

                NoteAddUpdateActivity.RESULT_DELETE -> {
                    val position = result.data?.getIntExtra(NoteAddUpdateActivity.EXTRA_POSITION, 0) as Int
                    adapter.removeItem(position)
                    snackBarMessage("Satu Item Berhasil Dihapus")
                }
            }


        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJustFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Notes"

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.setHasFixedSize(true)

        adapter = NoteAdapter( object  : NoteAdapter.OnItemClickCallBack {
            override fun onItemClicked(selected: Note?, position: Int?) {
                val intent = Intent(this@JustFormActivity, NoteAddUpdateActivity::class.java)
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, selected)
                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
                resultLauncher.launch(intent)
            }

        })

        binding.rvNotes.adapter = adapter

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@JustFormActivity, NoteAddUpdateActivity::class.java)
            resultLauncher.launch(intent)
        }

        if (savedInstanceState == null) {
            loadNotesAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<Note>(EXTRA_STATE)
            if (list != null) {
                adapter.listNote = list
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listNote)
    }

    private fun loadNotesAsync() {
        lifecycleScope.launch {
            binding.progressbar.visibility = View.VISIBLE
            val noteHelper = NoteHelper.getInstance(applicationContext)
            noteHelper.open()

            val deferreddNotes = async(Dispatchers.IO) {
                val cursor = noteHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            binding.progressbar.visibility = View.INVISIBLE
            val notes = deferreddNotes.await()
            if (notes.size > 0 ) {
                adapter.listNote = notes
            } else {
                adapter.listNote = ArrayList()
                snackBarMessage("Tidak ada data saat ini")
            }
            noteHelper.close()

        }
    }

    private fun snackBarMessage(message: String) {
        Snackbar.make(binding.rvNotes, message, Snackbar.LENGTH_SHORT).show()
    }


}