package com.example.tema2.Db.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tema2.ApplicationController;
import com.example.tema2.Db.AppDatabase;
import com.example.tema2.Db.Entity.User;

import java.util.List;

public class UserRepository {
    private AppDatabase appDatabase;
    public UserRepository(Context context) {
        appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertTask(final User user,
                           final OnUserRepositoryActionListener listener) {
        new InsertTask(listener).execute(user);
    }

    public User getUserByName(String name){
        return appDatabase.userDao().findByName(name);
    }

    public void getUsersTask(final OnGetUsersActionListener listener) {
        new GetUsersTask(listener).execute();
    }

    public void deleteUsersTask(String name, final OnDeleteUserActionListener listener) {
        new DeleteTask(listener).execute(name);
    }

    private class InsertTask extends AsyncTask<User, Void, Void> {
        OnUserRepositoryActionListener listener;
        InsertTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            appDatabase.userDao().insertAll(users[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }

    private class GetUsersTask extends AsyncTask<Void, Void, List<User>> {
        OnGetUsersActionListener listener;

        GetUsersTask(OnGetUsersActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return appDatabase.userDao().getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            listener.actionSuccess(users);
        }
    }

    private class DeleteTask extends AsyncTask<String, Void, Boolean> {
        OnDeleteUserActionListener listener;

        DeleteTask(OnDeleteUserActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Boolean doInBackground(String... names) {
            for(String name : names){
                User user = getUserByName(name);

                if(user == null){
                    return false;
                }

                appDatabase.userDao().delete(user);
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            listener.actionSuccess(result);
        }
    }
}
