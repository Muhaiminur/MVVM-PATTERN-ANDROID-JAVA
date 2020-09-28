package com.abir.mvvmsample.REPO;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abir.mvvmsample.HTTP.ApiClient;
import com.abir.mvvmsample.HTTP.BankService;
import com.abir.mvvmsample.MODEL.BANK;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankRepo {
    BankService apiInterface = ApiClient.getBaseClient().create(BankService.class);
    private BankService bankService;
    private MutableLiveData<List<BANK>> bankMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> progressbarObservable = new MutableLiveData<>();

    public void get_bank_list() {
        try {
            progressbarObservable.postValue(true);
            try {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Call<List<BANK>> call = apiInterface.get_bank();
                        call.enqueue(new Callback<List<BANK>>() {
                            @Override
                            public void onResponse(Call<List<BANK>> call, Response<List<BANK>> response) {
                                progressbarObservable.postValue(false);
                                if (response.body() != null) {
                                    Log.d("from server", response.body().size() + "");
                                    bankMutableLiveData.postValue(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<BANK>> call, Throwable t) {
                                progressbarObservable.postValue(false);
                                Log.d("Error", t.toString());
                                //bankMutableLiveData.postValue(null);
                            }
                        });
                    }
                }, 5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public LiveData<List<BANK>> getBankLiveData() {
        return bankMutableLiveData;
    }

    public MutableLiveData<Boolean> getProgress() {
        return progressbarObservable;
    }
}
