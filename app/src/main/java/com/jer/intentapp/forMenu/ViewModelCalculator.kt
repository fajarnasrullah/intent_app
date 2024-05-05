package com.jer.intentapp.forMenu

import androidx.lifecycle.ViewModel

class ViewModelCalculator: ViewModel() {

    var result = 0

    fun calculateResult(width: String, length: String, height: String) {
        result = width.toInt() * length.toInt() * height.toInt()
    }

}