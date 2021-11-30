package com.example.splitisfun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BillViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private  String gName;

    BillViewModelFactory(Application application, String gName) {
        this.application = application;
        this.gName = gName;
    }

    @NonNull
    @Override
    public  <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new com.example.splitisfun.BillViewModel(application, gName);
    }

}

