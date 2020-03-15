package com.example.tema2.Db.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tema2.Db.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    User findByName(String name);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
