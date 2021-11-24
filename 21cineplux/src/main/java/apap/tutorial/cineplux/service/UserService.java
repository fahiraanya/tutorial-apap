package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.UserModel;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();
    UserModel findUserById(String id);
    UserModel findUserByUsername(String username);
    boolean isMatch(String newPassword, String oldPassword);
    void setPassword(UserModel myUser, String newPassword);
    void deleteUser(UserModel user);
}
