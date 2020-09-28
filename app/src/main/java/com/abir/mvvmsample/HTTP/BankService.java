package com.abir.mvvmsample.HTTP;

import com.abir.mvvmsample.MODEL.BANK;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BankService {
    @GET("image_url.json")
    Call<List<BANK>> get_bank();
}
