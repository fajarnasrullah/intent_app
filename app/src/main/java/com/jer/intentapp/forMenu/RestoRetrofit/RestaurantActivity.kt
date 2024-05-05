package com.jer.intentapp.forMenu.RestoRetrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jer.intentapp.R
import com.jer.intentapp.databinding.ActivityRestaurantBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantBinding
    val viewModel: RestoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_restaurant)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)




        binding.btnSend.setOnClickListener { view ->
            val inputReview = binding.edReview.text.toString()
            viewModel.postReview(inputReview)
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        viewModel.restaurant.observe(this) { restaurant ->
            setRestaurantData(restaurant)
        }

        viewModel.listReview.observe(this) {customerReview ->
            setReviewData(customerReview)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }







    private fun setRestaurantData(restaurant: Restaurant) {
        binding.tvTitle.text = restaurant.name
        binding.tvDescription.text = restaurant.description
        Glide.with(this@RestaurantActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(binding.ivPicture)
    }

    private fun setReviewData(reviewsItem: List<CustomerReviewsItem>) {
        val adapter = ReviewAdapter()
        adapter.submitList(reviewsItem)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else View.GONE
    }
}