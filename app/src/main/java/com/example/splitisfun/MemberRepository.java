package com.example.splitisfun;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.splitisfun.AppDatabase;

import java.util.ArrayList;
import java.util.List;
/* Use AsyncTask to perform operations insert,delete,update,deleteAll,getAllMembersNonLive. Doing these operations on the main thread could
 * lock our user interface */

public class MemberRepository {
    private com.example.splitisfun.MemberDao memberDao;
    private LiveData<List<com.example.splitisfun.MemberEntity>> allMembers;

    MemberRepository(Application application, String gName) {
        AppDatabase database = AppDatabase.getInstance(application);
        memberDao = database.memberDao();
        allMembers = memberDao.getAll(gName);
    }

    public void insert(com.example.splitisfun.MemberEntity member) {
        new InsertMemberAsyncTask(memberDao).execute(member);
    }

    public void delete(com.example.splitisfun.MemberEntity member) {
        new DeleteMemberAsyncTask(memberDao).execute(member);
    }

    public void update(com.example.splitisfun.MemberEntity member) {
        new UpdateMemberAsyncTask(memberDao).execute(member);
    }

    public void deleteAll(String gName) {
        new DeleteAllAsyncTask(memberDao).execute(gName);
    }

    LiveData<List<com.example.splitisfun.MemberEntity>> getAllMembers() {
        return allMembers;
    }

    List<com.example.splitisfun.MemberEntity> getAllMembersNonLive(String gName) {
        GetAllMembersNonLiveAsyncTask members = new GetAllMembersNonLiveAsyncTask(memberDao);
        try {
            return members.execute(gName).get();
        } catch (Exception err) {
            return new ArrayList<>(); // if there are no members in database return a blank array list
        }
    }

    private static class GetAllMembersNonLiveAsyncTask extends AsyncTask<String,Void,List<com.example.splitisfun.MemberEntity>> {
        private com.example.splitisfun.MemberDao memberDao;

        private GetAllMembersNonLiveAsyncTask(com.example.splitisfun.MemberDao memberDao) {
            this.memberDao = memberDao;
        }

        @Override
        protected List<com.example.splitisfun.MemberEntity> doInBackground(String... strings) {
            return memberDao.getAllNonLive(strings[0]);
        }
    }


    private static class InsertMemberAsyncTask extends AsyncTask<com.example.splitisfun.MemberEntity,Void,Void> {
        private com.example.splitisfun.MemberDao memberDao;

        private InsertMemberAsyncTask(com.example.splitisfun.MemberDao memberDao) {
            this.memberDao = memberDao;
        }

        @Override
        protected Void doInBackground(com.example.splitisfun.MemberEntity... memberEntities) {
            memberDao.insert(memberEntities[0]);
            return null;
        }
    }

    private static class DeleteMemberAsyncTask extends AsyncTask<com.example.splitisfun.MemberEntity,Void,Void> {
        private com.example.splitisfun.MemberDao memberDao;

        private DeleteMemberAsyncTask(com.example.splitisfun.MemberDao memberDao) {
            this.memberDao = memberDao;
        }

        @Override
        protected Void doInBackground(com.example.splitisfun.MemberEntity... memberEntities) {
            memberDao.delete(memberEntities[0]);
            return null;
        }
    }

    private static class UpdateMemberAsyncTask extends AsyncTask<com.example.splitisfun.MemberEntity,Void,Void> {
        private com.example.splitisfun.MemberDao memberDao;

        private UpdateMemberAsyncTask(com.example.splitisfun.MemberDao memberDao) {
            this.memberDao = memberDao;
        }

        @Override
        protected Void doInBackground(com.example.splitisfun.MemberEntity... memberEntities) {
            memberDao.update(memberEntities[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<String,Void,Void> {
        private com.example.splitisfun.MemberDao memberDao;

        private DeleteAllAsyncTask(com.example.splitisfun.MemberDao memberDao) {
            this.memberDao = memberDao;
        }


        @Override
        protected Void doInBackground(String... strings) {
            memberDao.deleteAll(strings[0]);
            return null;
        }
    }
}
