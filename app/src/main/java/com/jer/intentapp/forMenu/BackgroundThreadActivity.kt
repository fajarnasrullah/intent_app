package com.jer.intentapp.forMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.jer.intentapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class BackgroundThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_thread)


        val btnTest: Button = findViewById(R.id.btnTest)
//        val tvCompress: TextView = findViewById(R.id.tvCompressing)
        val tvStatus: TextView = findViewById(R.id.tvStatus)

//        val executor = Executors.newSingleThreadExecutor()
//        val handler = Handler(Looper.getMainLooper())

//        btnTest.setOnClickListener {
//            executor.execute {
//                try {
//
//                    for (i in 0..10) {
//                        Thread.sleep(500)
//                        var percentage = i * 10
//                        handler.post{
//                            //update ui in main thread
//                            if (percentage == 100) {
//                            tvStatus.setText(R.string.complete_text)
//                            }
//                            else tvStatus.text= String.format(getString(R.string.compressing_text, percentage))
//                        }
//                    }
//
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//        }

        btnTest.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default) {
                for (i in 0..10) {
                    delay(500)
                    var percentage = i * 10
                    withContext(Dispatchers.Main) {
                        if (percentage == 100) {
                            tvStatus.setText(R.string.complete_text)
                        }
                        else tvStatus.text= String.format(getString(R.string.compressing_text, percentage))
                    }
                }

            }
        }

    }
}