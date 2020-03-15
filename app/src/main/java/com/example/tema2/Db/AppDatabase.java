package com.example.tema2.Db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tema2.Db.Dao.UserDao;
import com.example.tema2.Db.Entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
