package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDB userDB;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }
    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getListUser() { return userDB.findAll(); }

    @Override
    public UserModel findUserById(String id) {
        return userDB.findById(id);
    }

    @Override
    public UserModel findUserByUsername(String username) {
        return userDB.findByUsername(username);
    }

    @Override
    public boolean isMatch(String newPassword, String oldPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(newPassword, oldPassword);
    }
    @Override
    public void setPassword(UserModel user, String newPassword) {
        user.setPassword(newPassword);
    }

    @Override
    public void deleteUser(UserModel user) {
        userDB.delete(user);
    }
}
