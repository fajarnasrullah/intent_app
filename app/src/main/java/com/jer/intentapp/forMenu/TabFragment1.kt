package com.jer.intentapp.forMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jer.intentapp.R


class TabFragment1 : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvLabel: TextView = view.findViewById(R.id.tvFragment1)
        val tvLabel2: TextView = view.findViewById(R.id.tv2Fragement1)

        val index = arguments?.getInt(GET_NUMBER, 0)
        val name = arguments?.getString(GET_STRING)
        tvLabel.text = getString(R.string.content_tab_text_1, index)
        tvLabel2.text = name





    }

    companion object {
        const val GET_NUMBER = "index_number"
        const val GET_STRING = "app_name"
    }
}