package com.example.findroutesappnew;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder
{
    public static GeocodingService getGeocodingService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GeocodingService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GeocodingService.class);
    }
}
