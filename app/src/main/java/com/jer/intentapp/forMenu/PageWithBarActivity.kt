package com.jer.intentapp.forMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jer.intentapp.JustPage2
import com.jer.intentapp.R
import com.jer.intentapp.databinding.ActivityPageWithBarBinding
import com.jer.intentapp.forMenu.RestoRetrofit.RestaurantActivity

class PageWithBarActivity : AppCompatActivity(){

    private lateinit var binding: ActivityPageWithBarBinding

    companion object {
        const val EXTRA_EXTRA = "extra_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageWithBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
//                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(this@PageWithBarActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
//        intent.getStringExtra(EXTRA_EXTRA)

        binding.searchBar.inflateMenu(R.menu.menu_layout)
        binding.searchBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId){
                R.id.menu1 -> {
                    binding.searchView.hide()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, ArticleFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.menu2 -> {
                    val intent = Intent(this@PageWithBarActivity, JustPage2::class.java)
                    startActivity(intent)
                    true
                }

                R.id.menu3 -> {
                    val intent = Intent(this@PageWithBarActivity, ForTabLayoutActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.menu4 -> {
                    val intent = Intent(this@PageWithBarActivity, BackgroundThreadActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.menu5 -> {
                    val intent = Intent(this@PageWithBarActivity, RestaurantActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.menu6 -> {
                    val intent = Intent(this@PageWithBarActivity, ForVMActivity::class.java)
                    startActivity(intent)
                    true
                }


                else -> false
            }
            true

        }
//        binding.topappBar.setOnMenuItemClickListener {menuItem ->
//            when (menuItem.itemId){
//                R.id.menu1 -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_container, ArticleFragment())
//                        .addToBackStack(null)
//                        .commit()
//                    true
//                }
//                R.id.menu2 -> {
//                    val intent = Intent(this@PageWithBarActivity, JustPage2::class.java)
//                    startActivity(intent)
//                    true
//                }
//                else -> false
//            }
//
//        }

    }


}