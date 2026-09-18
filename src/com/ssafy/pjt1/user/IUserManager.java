package com.ssafy.pjt1.user;

public interface IUserManager {
    void register(String id, String password, String name, int age,
                  double height, double weight, String disease);
    void login(String id, String password);
    void logout();
    boolean isLoggedIn();
    UserDomain getMyInfo();
    void updateMyInfo(String name, int age, double height, double weight, String disease);
    void changePassword(String currentPassword, String newPassword);
    void withdraw(String currentPassword);
}
