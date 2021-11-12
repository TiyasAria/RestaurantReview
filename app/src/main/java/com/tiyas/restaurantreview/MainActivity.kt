package com.tiyas.restaurantreview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.tiyas.restaurantreview.adapter.ReviewAdapter
import com.tiyas.restaurantreview.databinding.ActivityMainBinding
import com.tiyas.restaurantreview.model.CustomerReview
import com.tiyas.restaurantreview.model.PostReviewResponse
import com.tiyas.restaurantreview.model.Restaurant
import com.tiyas.restaurantreview.model.RestaurantResponse
import com.tiyas.restaurantreview.service.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    companion object{
        private const  val  TAG = "MainActivity"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        supportActionBar?.hide()

//         melakukan inisialisasi viewModel
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.restaurant.observe(this, {
            restaurant -> setRestaurantData(restaurant)
        })

        mainViewModel.listReview.observe(this, {
            cunsomerReview -> setReviewData(cunsomerReview)
        })

        mainViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        val layoutManager = LinearLayoutManager(this)
        mainBinding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        mainBinding.rvReview.addItemDecoration(itemDecoration)

        mainViewModel.snackbarText.observe(this, {
            it.getContentIfNotHandled()?.let {
                 Snackbar.make(
                window.decorView.rootView, it, Snackbar.LENGTH_SHORT
            ).show()
            }
        })


//        button untuk send review
        mainBinding.btnSend.setOnClickListener {
            view -> mainViewModel.postReview(mainBinding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setReviewData(customerReviews: List<CustomerReview>) {
        val listReview = customerReviews.map {
            "${it.review}\n- ${it.name}"
        }
        val adapter = ReviewAdapter(listReview)
        mainBinding.rvReview.adapter = adapter
        mainBinding.edReview.setText("")
    }

    private fun setRestaurantData(restaurant: Restaurant) {
      mainBinding.tvTitle.text = restaurant.name
      mainBinding.tvDesc.text = restaurant.description
      Glide.with(this@MainActivity)
          .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
          .into(mainBinding.ivPicture)

    }

    private fun showLoading(isLoading : Boolean) {
        mainBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}