package com.id.maris.suittest.service;

import com.id.maris.suittest.model.GuestModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by almas on 06-Sep-16.
 */

public interface ApiService {

    //discover movie
    @GET("api/people")
    Call<List<GuestModel>> getGuest(@Path("guest") String guest);


}