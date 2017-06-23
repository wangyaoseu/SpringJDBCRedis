package com.springmvc.controller;
import com.springmvc.service.UserService;
import com.springmvc.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangyao on 2017/6/23.
 */

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/toMyProfile.do")
    public ModelAndView toMyProfile(Model model) {
        User user = userService.getProfile(2);
        model.addAttribute("user",user);
        System.out.println(user.getUid()+"***"+user.getUsername());
        return new ModelAndView("myProfile", model.asMap());
    }
}
