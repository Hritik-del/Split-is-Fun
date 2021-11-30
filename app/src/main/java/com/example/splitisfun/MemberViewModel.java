package com.example.splitisfun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MemberViewModel extends AndroidViewModel {
    private com.example.splitisfun.MemberRepository repository;
    private LiveData<List<com.example.splitisfun.MemberEntity>> allMembers;

    MemberViewModel(@NonNull Application application, String gName) {
        super(application);
        repository = new com.example.splitisfun.MemberRepository(application,gName);
        allMembers = repository.getAllMembers();
    }

    public void insert(com.example.splitisfun.MemberEntity member) {
        repository.insert(member);
    }

    public void update(com.example.splitisfun.MemberEntity member) {
        repository.update(member);
    }

    public void delete(com.example.splitisfun.MemberEntity member) {
        repository.delete(member);
    }

    public void deleteAll(String gName) {
        repository.deleteAll(gName);
    }

    LiveData<List<com.example.splitisfun.MemberEntity>> getAllMembers() {
        return allMembers;
    }

    List<com.example.splitisfun.MemberEntity> getAllMembersNonLiveData(String gName) {
        return repository.getAllMembersNonLive(gName);
    }

}
