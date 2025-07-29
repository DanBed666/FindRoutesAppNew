package com.example.findroutesappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.Map;

public class PlaceViewModel
{
    PlaceRepository placeRepository;

    public MutableLiveData<Place> getPlace(Map<String, String> options)
    {
        placeRepository = new PlaceRepository();

        return placeRepository.getPlace(options);
    }
}
