package com.example.findroutesappnew;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GeocodingService
{
    String BASE_URL = "https://eu1.locationiq.com/v1/";

    @GET("reverse")
    Call<Place> getPlace(@QueryMap Map<String, String> options);
}
