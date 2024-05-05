package com.jer.intentapp.forMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.jer.intentapp.R
import com.jer.intentapp.databinding.ActivitySaveTextFileBinding

class SaveTextFileActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySaveTextFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveTextFileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNew.setOnClickListener(this)
        binding.buttonSave.setOnClickListener(this)
        binding.buttonOpen.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_new -> newFile()
                R.id.button_save -> showList()
            R.id.button_open -> saveFile()
        }
    }


    fun newFile() {
        binding.editTitle.setText("")
        binding.editFile.setText("")
        Toast.makeText(this, "Clearing File", Toast.LENGTH_SHORT).show()
    }

    fun showList() {
        val items = fileList()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pilih file yang diingingkan")
        builder.setItems(items) {dialog, item -> loadData(items[item].toString())}
        val alert = builder.create()
        alert.show()
    }

    fun loadData(title: String) {
        val fileModel = FileHelper.readFromFile(title, this)
        binding.editTitle.setText(fileModel.fileName)
        binding.editFile.setText(fileModel.data)
        Toast.makeText(this, "Loading " + fileModel.fileName + " data", Toast.LENGTH_SHORT).show()

    }

    fun saveFile() {

        when {
            binding.editTitle.text.toString().isEmpty() -> Toast.makeText(this, "Title ini tidak boleh kosong", Toast.LENGTH_SHORT).show()
            binding.editFile.text.toString().isEmpty() -> Toast.makeText(this, "Konten ini tidak boleh kosong", Toast.LENGTH_SHORT).show()

            else -> {
                val title = binding.editTitle.text.toString()
                val data = binding.editFile.text.toString()
                val fileModel = FileModel()
                fileModel.fileName = title
                fileModel.data = data
                FileHelper.writeToFile(fileModel, this)
                Toast.makeText(this, "Saving" + fileModel.fileName + "file", Toast.LENGTH_LONG).show()

            }
        }

    }
}