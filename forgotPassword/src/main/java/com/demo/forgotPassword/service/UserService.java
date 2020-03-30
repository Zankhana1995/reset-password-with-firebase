package com.demo.forgotPassword.service;

import com.demo.forgotPassword.dto.UserDTO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.demo.forgotPassword.domain.User;
import com.demo.forgotPassword.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private FirebaseAuth fbAuth;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(String email) {
        try {
            String str = FirebaseAuth.getInstance().generatePasswordResetLink(email);
            System.out.println("Link ===> "+str);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        Optional<User> user = userRepository.findById(email);
        return user.get();
    }

    @Transactional
    public User addUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public User updateUser(String email, UserDTO userDTO) {
        User updateUser = findUserById(email);
        try {
            String link = FirebaseAuth.getInstance().generatePasswordResetLink(email);
            BeanUtils.copyProperties(userDTO, updateUser);
            updateUser = userRepository.save(updateUser);
        }
        catch (FirebaseAuthException e)
        {
            System.out.println("Error generating email link: " + e.getMessage());
        }
        return updateUser;
    }
}
