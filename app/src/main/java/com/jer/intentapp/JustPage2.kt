package com.jer.intentapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.jer.intentapp.databinding.ActivityJustPage2Binding

class JustPage2 : AppCompatActivity(){


    companion object {
        const val EXTRA_STRING = "extra_string"
        const val EXTRA_INT = "extra_int"
    }

    private lateinit var binding: ActivityJustPage2Binding
    private val itsList = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJustPage2Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
//        setContentView(R.layout.activity_just_page2)

        binding.rvHeroes.setHasFixedSize(true)
        itsList.addAll(addItemRV())
        showRV()


//        var tvPage2 = findViewById<TextView>(R.id.tvSecondPage)
//        var tvPage3 = findViewById<TextView>(R.id.tvSecondPage2)


        val string = intent.getStringExtra(EXTRA_STRING)
        val integer = intent.getIntExtra(EXTRA_INT,0)

        //untuk Parcelable
        val forParc = if (Build.VERSION.SDK_INT >= 33 ) {
            intent.getParcelableExtra<Orang>(EXTRA_STRING, Orang::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Orang>(EXTRA_STRING)
        }

        if ( forParc != null) {
            val text3 = "Halo saya ${forParc.nama.toString()}, berprofesi sebagai ${forParc.profesi.toString()} dan saat ini usia nya ${forParc.umur.toString()}"
            binding.tvSecondPage2.text = text3
//            tvPage3.text = text3
        }

        val text2 = "ini $string dan juga ini $integer"
        binding.tvSecondPage.text = text2
//        tvPage2.text = text2


    }
    

    fun addItemRV(): ArrayList<Hero>{
        val getName = resources.getStringArray(R.array.data_name)
        val getDesc = resources.getStringArray(R.array.data_description)
        val getPhoto = resources.getStringArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()
        for (i in getName.indices) {
            val heroByIndex = Hero(getName[i], getDesc[i], getPhoto[3])
            listHero.add(heroByIndex)
        }

        return listHero
    }

    fun showRV() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        val heroesAdapter = HeroesAdapter(itsList)
        binding.rvHeroes.adapter =heroesAdapter
    }

}