package com.neta.systems.places.api

import com.neta.systems.places.data.model.Post
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PlacesApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

}