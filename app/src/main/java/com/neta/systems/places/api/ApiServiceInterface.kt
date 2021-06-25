package com.neta.systems.places.api

import com.neta.systems.places.data.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiServiceInterface {

    /*
    * Definition of access points - RapidAPI
    * */

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @Headers(
        "Accept: */*",
        "User-Agent: WeatherApp",
        "x-rapidapi-key: d3fe3a34c5mshf70e1bb90c464a8p1ba4aajsn4233972e8a9c",
        "x-rapidapi-host: community-open-weather-map.p.rapidapi.com"

    )
    @GET("/weather?q=London%2Cuk&lat=0&lon=0&callback=test&id=2172797&lang=null&units=%22metric%22%20or%20%22imperial%22&mode=xml%2C%20html")
    fun getWeatherMarker (): Observable<String>

}