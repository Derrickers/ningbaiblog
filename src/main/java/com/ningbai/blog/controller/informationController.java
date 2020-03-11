package com.ningbai.blog.controller;

import com.ningbai.blog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class informationController {

    @GetMapping("/info/{id}/{part}")
    public String Info(HttpServletResponse response,
                       HttpServletRequest request,
                       Model model,
                       @PathVariable(name = "id") Long id,
                       @PathVariable(name = "part") String part){
        User user = (User)request.getSession().getAttribute("user");
        if(id.equals(user.getId())){
            return "myinformation";
        }else{
            return "notmyinformation";
        }


    }
}
