package com.example.wallpaper_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaper.Adapter.WallpaperAdapter
import com.example.wallpaper_app.API.ApiClient
import com.example.wallpaper_app.API.ApiInterface
import com.example.wallpaper_app.Model.PhotoModel
import com.example.wallpaper_app.Model.PhotosItem
import com.example.wallpaper_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: WallpaperAdapter
    var auth = "5pbl8vXz6P90sbnheyvSm3783boc0r1mMMYqAGVJYKqOA6rX3ALTrNg8"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = WallpaperAdapter()
        binding.btnsearch.setOnClickListener {
            callApi()
        }
    }

    private fun callApi() {

        var txt = binding.edtsearch.text.toString()

        var api: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getphotos(auth, txt).enqueue(object : Callback<PhotoModel> {

            override fun onResponse(call: Call<PhotoModel>, response: Response<PhotoModel>) {
                if (response.isSuccessful) {
                    var photos = response.body()?.photos
                    adapter.setPhotos(photos as List<PhotosItem>?)
                    binding.rcvphotos.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    binding.rcvphotos.adapter = adapter

                }

            }

            override fun onFailure(call: Call<PhotoModel>, t: Throwable) {

            }

        })

    }



}