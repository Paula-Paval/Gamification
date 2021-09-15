package com.anylyze.gamification.endpoints;

import com.anylyze.gamification.data.UserData;
import com.anylyze.gamification.exceptions.UserAlreadyExistsException;
import com.anylyze.gamification.service.UserService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


@Controller
public class UserEndpoint {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserEndpoint.class);

    @PostMapping("/register")
    public ResponseEntity userRegistration(final @RequestBody @Valid UserData userData, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("bindingResult has Errors");
            model.addAttribute("registrationForm", userData);
            return ResponseEntity.status(403).build();
        }
        try {
            return ResponseEntity.ok(userService.register(userData));
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("email", "userData.email", "An account exist for this email");
            model.addAttribute("registrationForm", userData);
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity showDetails(@PathVariable Long id) {
        return ResponseEntity.ok(userService.showDetails(id));

    }

    @GetMapping("/items-reward-shop")
    public RedirectView getRewardShop() {
        return new RedirectView("/rewards");
    }

    @GetMapping("/buy-reward/{userId}/{rewardId}")
    public ResponseEntity buyreward(@PathVariable Long userId, @PathVariable Long rewardId) {
        try {
            return ResponseEntity.ok(userService.buyItem(userId, rewardId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}