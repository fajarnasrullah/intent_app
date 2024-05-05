package com.jer.intentapp.forMenu.RestoRetrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestoViewModel: ViewModel() {


    val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> = _restaurant

    val _listReview = MutableLiveData<List<CustomerReviewsItem>>()
    val listReview: LiveData<List<CustomerReviewsItem>> = _listReview

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    companion object {
        private const val TAG = "MainActivity"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }


    init {
        findResto()
    }


    fun findResto() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getResto(RESTAURANT_ID)
        client.enqueue(object: Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _restaurant.value = response.body()?.restaurant

                } else Log.e(TAG, "onFailure: ${response.message()}")
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }


    fun postReview(review: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().postReview(RESTAURANT_ID, "User", review)
        client.enqueue(object : Callback<PostReviewResponse> {
            override fun onResponse(
                call: Call<PostReviewResponse>,
                response: Response<PostReviewResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listReview.value = response.body()?.customerReviews
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }


}