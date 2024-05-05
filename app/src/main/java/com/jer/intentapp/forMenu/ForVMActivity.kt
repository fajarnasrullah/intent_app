package com.jer.intentapp.forMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jer.intentapp.R
import com.jer.intentapp.databinding.ActivityForVmactivityBinding


class ForVMActivity : AppCompatActivity() {

    private lateinit var binding : ActivityForVmactivityBinding
    val viewModel: ViewModelCalculator by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForVmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayResult()

        binding.btnCalculate.setOnClickListener {

            var length = binding.edtLength.text.toString()
            var width = binding.edtWidth.text.toString()
            var height = binding.edtHeight.text.toString()

            when {
                length.isEmpty() -> binding.edtLength.error = "kolom length kosong"
                width.isEmpty() -> binding.edtWidth.error = "kolom width kosong"
                height.isEmpty() -> binding.edtHeight.error = "kolom height kosong"

                else -> {
                    viewModel.calculateResult(width, length, height)
                    displayResult()
                }
            }
        }
    }



    private fun displayResult() {

        binding.tvResult.text = viewModel.result.toString()
    }
}