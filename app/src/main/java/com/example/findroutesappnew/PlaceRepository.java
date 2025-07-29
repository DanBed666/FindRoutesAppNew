package com.example.findroutesappnew;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceRepository
{
    public MutableLiveData<Place> getPlace(Map<String, String> options)
    {
        MutableLiveData<Place> placeMutableLiveData = new MutableLiveData<>();

        RetrofitBuilder.getGeocodingService().getPlace(options).enqueue(new Callback<Place>()
        {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response)
            {
                placeMutableLiveData.setValue(response.body());
                Log.i("REPO", "działa");
            }

            @Override
            public void onFailure(Call<Place> call, Throwable t)
            {
                Log.e("REPO", Objects.requireNonNull(t.getMessage()));
            }
        });

        return placeMutableLiveData;
    }
}
