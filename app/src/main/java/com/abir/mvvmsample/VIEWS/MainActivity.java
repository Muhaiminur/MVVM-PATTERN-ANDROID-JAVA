package com.abir.mvvmsample.VIEWS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.abir.mvvmsample.ADAPTER.Banklistadapter;
import com.abir.mvvmsample.MODEL.BANK;
import com.abir.mvvmsample.R;
import com.abir.mvvmsample.REPO.BankRepo;
import com.abir.mvvmsample.VIEWMODELS.Mainviewmodel;
import com.abir.mvvmsample.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Mainviewmodel mainviewmodel;
    MutableLiveData<List<BANK>> bankMutableLiveData;
    List<BANK> bankList = new ArrayList<>();
    ActivityMainBinding mainBinding;
    ProgressDialog progressDialog;
    Banklistadapter banklistadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mainviewmodel = ViewModelProviders.of(MainActivity.this).get(Mainviewmodel.class);
        mainviewmodel.init();
        observeLogin();
        initial_list();
        mainviewmodel.getVolumesResponseLiveData().observe(MainActivity.this, new Observer<List<BANK>>() {
            @Override
            public void onChanged(List<BANK> volumesResponse) {
                if (volumesResponse != null) {
                    Log.d("item change", volumesResponse.size() + "");
                    mainBinding.bankListCount.setText("abir " + volumesResponse.size());
                    bankList.clear();
                    bankList.addAll(volumesResponse);
                    banklistadapter.notifyDataSetChanged();
                }
            }
        });

        progressDialog = new ProgressDialog(this);

    }

    private void observeLogin() {
        mainviewmodel.getProgressbar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean progressObserve) {
                if (progressObserve) {
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Loading ...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.show();
                } else {
                    progressDialog.cancel();
                }
            }
        });
    }

    public void initial_list() {
        banklistadapter = new Banklistadapter(bankList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mainBinding.bankList.setLayoutManager(mLayoutManager);
        mainBinding.bankList.setItemAnimator(new DefaultItemAnimator());
        mainBinding.bankList.setAdapter(banklistadapter);
    }
}