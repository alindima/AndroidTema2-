package com.example.tema2.Db.Repository;

import com.example.tema2.Db.Entity.User;

import java.util.List;

public interface OnGetUsersActionListener {
    void actionSuccess(List<User> users);
}
