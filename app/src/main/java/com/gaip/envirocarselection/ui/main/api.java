package com.gaip.envirocarselection.ui.main;

import android.app.Person;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface api {
    @GET("manufacturers")
    Call<List<manufacturer>> getUserData();
}

