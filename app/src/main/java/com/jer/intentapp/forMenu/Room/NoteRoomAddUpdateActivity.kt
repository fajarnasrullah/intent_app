package com.jer.intentapp.forMenu.Room

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.jer.intentapp.R
import com.jer.intentapp.databinding.ActivityNoteAddUpdateBinding
import com.jer.intentapp.databinding.ActivityNoteRoomAddUpdateBinding
import com.jer.intentapp.forMenu.SQLite.NoteAddUpdateActivity

class NoteRoomAddUpdateActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
    private var isEdit = false
    private var note: NoteForRoom? = null

    private lateinit var viewModel: VMNoteRoomAdd
    private var _binding: ActivityNoteRoomAddUpdateBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteRoomAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = obtainViewModel(this@NoteRoomAddUpdateActivity)


        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            isEdit = true
        } else {
            note = NoteForRoom()
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)

            if (note != null) {
                note?.let {
                    binding?.edtTitle?.setText(it.title)
                    binding?.edtDescription?.setText(it.description)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.btnSubmit?.text = btnTitle

        binding?.btnSubmit?.setOnClickListener{
            val title = binding?.edtTitle?.text.toString().trim()
            val desc = binding?.edtDescription?.text.toString().trim()

            when {
                title.isEmpty() -> {
                    binding?.edtTitle?.error = getString(R.string.empty)
                }

                desc.isEmpty() -> {
                    binding?.edtDescription?.error = getString(R.string.empty)
                }

                else -> {
                    note.let {
                        it?.title = title
                        it?.description = desc
                    }

                    val intent = Intent(this@NoteRoomAddUpdateActivity, NoteRoomActivity::class.java)
                    startActivity(intent)
//                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
//                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, note)


                    if (isEdit) {
                        viewModel.update(note as NoteForRoom)
                        showToast(getString(R.string.change))
                    } else {

                        note.let {note ->
                            note?.date = DateHelper.getCurrentDate()
                        }

                        viewModel.insert(note as NoteForRoom)
                        showToast(getString(R.string.added))
                    }
                    finish()
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAlertDialog(ALERT_DIALOG_CLOSE)
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): VMNoteRoomAdd {
        val factory = VMFactoryRoom.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(VMNoteRoomAdd::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }

        return super.onOptionsItemSelected(item)
    }

    fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String

        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogTitle = getString(R.string.delete)
            dialogMessage = getString(R.string.message_delete)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) {_, _, ->
                if (!isDialogClose) {
                    viewModel.delete(note as NoteForRoom)
                    showToast(getString(R.string.deleted))
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) {
                    dialog, _ -> dialog.cancel()
                finish()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}