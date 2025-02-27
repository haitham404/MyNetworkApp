package com.example.mynetworkapp;

public class UserSession {
    private static UserSession instance;
    private static User currentUser;



    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public static void setUser(User user) {
        currentUser=user;
    }

    public void setUserId(int id) {
        currentUser.setId(id);
    }

    public void setUsername(String name) {
        currentUser.setName(name); // Assuming User has setName method
    }

    public User getUser() {
        return currentUser;
    }

    public static void clear() {
        instance = null;
    }

    public static boolean isLoggedIn() {
        return instance != null && instance.currentUser != null;
    }
}