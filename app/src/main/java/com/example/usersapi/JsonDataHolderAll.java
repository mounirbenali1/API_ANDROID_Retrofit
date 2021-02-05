package com.example.usersapi;

// this class to fetch all users we gonna have a list of users

import java.util.List;

public class JsonDataHolderAll {
    private List<UserInfo> data;
    public List<UserInfo> getAll() {
        return data;
    }
}
