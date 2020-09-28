package com.abir.mvvmsample.VIEWMODELS;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abir.mvvmsample.MODEL.BANK;
import com.abir.mvvmsample.REPO.BankRepo;

import java.util.List;

public class Mainviewmodel extends AndroidViewModel {
    private BankRepo bankRepo;
    private LiveData<List<BANK>> bankLiveData;
    private MutableLiveData<Boolean> progressbarObservable;

    public Mainviewmodel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        bankRepo = new BankRepo();
        searchbanklist();
        bankLiveData = bankRepo.getBankLiveData();
        progressbarObservable = bankRepo.getProgress();
    }

    public void searchbanklist() {
        bankRepo.get_bank_list();
    }

    public LiveData<List<BANK>> getVolumesResponseLiveData() {
        return bankLiveData;
    }


    public MutableLiveData<Boolean> getProgressbar() {
        return progressbarObservable;
    }

    public void oncheckClicked() {
        searchbanklist();
    }
}
