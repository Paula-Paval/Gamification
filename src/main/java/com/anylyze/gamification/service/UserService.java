package com.anylyze.gamification.service;

import com.anylyze.gamification.data.UserData;
import com.anylyze.gamification.data.UserRegistration;
import com.anylyze.gamification.exceptions.UserAlreadyExistsException;
import com.anylyze.gamification.model.Product;
import com.anylyze.gamification.model.Role;
import com.anylyze.gamification.model.User;
import com.anylyze.gamification.repository.ProductRepository;
import com.anylyze.gamification.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final static String USER_ALREADY_EXISTS_MSG = "user with email %s already exists";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserData register(UserData user) throws UserAlreadyExistsException {
        if (checkIfUserExist(user.getEmail())) {
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MSG, user.getEmail()));
        }
        User userentity = new User();
        BeanUtils.copyProperties(user, userentity);
        userentity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userentity.setRole(Role.GENERIC);
        userRepository.save(userentity);
        return user;
    }


    public boolean checkIfUserExist(String email) {
        List<User> users = userRepository.findAll();
        return users.stream().anyMatch(user -> user.getEmail().contains(email));
    }

    public UserRegistration showDetails(Long id) {
        User user = userRepository.getOne(id);
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setName(user.getName());
        userRegistration.setEmail(user.getEmail());
        userRegistration.setCredits(user.getCredits());
        userRegistration.setExperience(user.getExperience());
        return userRegistration;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String buyItem(Long userId, Long rewardId) {
        User user = userRepository.getOne(userId);
        Product reward = productRepository.getOne(rewardId);
        if (user.getCredits() - reward.getCredits() > 0) {
            user.setCredits(user.getCredits() - reward.getCredits());
            userRepository.save(user);
            return "Bought successfully!";
        } else
            return "You don't have enough credits!";

    }
}
