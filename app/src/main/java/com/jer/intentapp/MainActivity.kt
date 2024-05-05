package com.jer.intentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.jer.intentapp.forMenu.DataStore.DataStoreActivity
import com.jer.intentapp.forMenu.PageSharedPreferences.SharedPreferencesActivity
import com.jer.intentapp.forMenu.PageWithBarActivity
import com.jer.intentapp.forMenu.Room.NoteRoomActivity
import com.jer.intentapp.forMenu.Room.NoteRoomAddUpdateActivity
import com.jer.intentapp.forMenu.SQLite.JustFormActivity
import com.jer.intentapp.forMenu.SettingsPreferences.SettingsPreferencesActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ActivityMoveForResult.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getIntExtra(ActivityMoveForResult.EXTRA_STRING, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btnMove = findViewById<Button>(R.id.btn_move)
        btnMove.setOnClickListener(this)

        var btnMove2 = findViewById<Button>(R.id.btn_move2)
        btnMove2.setOnClickListener(this)

        var btnMove3 = findViewById<Button>(R.id.btn_move3)
        btnMove3.setOnClickListener(this)

        var btnMove4 = findViewById<Button>(R.id.btn_move4)
        btnMove4.setOnClickListener(this)

        var btnMove5 = findViewById<Button>(R.id.btnMove5)
        btnMove5.setOnClickListener(this)

        var btnMove6 = findViewById<Button>(R.id.btnMove6)
        btnMove6.setOnClickListener(this)

        var btnMove7 = findViewById<Button>(R.id.btnMove7)
        btnMove7.setOnClickListener(this)

        var btnMove8 = findViewById<Button>(R.id.btnMove8)
        btnMove8.setOnClickListener(this)

        tvResult = findViewById(R.id.tvResult)


    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_move -> {
                val movePage = Intent(this@MainActivity, JustPage2::class.java)
                startActivity(movePage)

            }

            R.id.btn_move2 -> {
                val movePage2 = Intent(this@MainActivity, JustPage2::class.java)
                movePage2.putExtra(JustPage2.EXTRA_STRING, "Just move page with data")
                movePage2.putExtra(JustPage2.EXTRA_INT,100)
                startActivity(movePage2)
            }

            R.id.btn_move3 -> {

                val orang = Orang("Fajar", 21, "Software Engineer")

                val movePage3 = Intent(this@MainActivity, JustPage2::class.java)
                movePage3.putExtra(JustPage2.EXTRA_STRING, orang)
                startActivity(movePage3)
            }

            R.id.btn_move4 -> {
                val number = "08123456789"

                val movePage4 = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $number"))
                startActivity(movePage4)
            }

            R.id.btnMove5 -> {
                val movePage5 = Intent(this@MainActivity, ActivityMoveForResult::class.java)
                resultLauncher.launch(movePage5)

            }

            R.id.btnMove6 -> {
                val movePage6 = Intent(this@MainActivity, PageWithBarActivity::class.java)
                startActivity(movePage6)
            }

            R.id.btnMove7 -> {
                val movePage7 = Intent(this@MainActivity, SharedPreferencesActivity::class.java)
                startActivity(movePage7)
            }

            R.id.btnMove8 -> {
                val movePage8 = Intent(this@MainActivity, NoteRoomActivity::class.java)
                startActivity(movePage8)
            }


        }
    }


}