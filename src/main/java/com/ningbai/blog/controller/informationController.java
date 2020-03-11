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

    @GetMapping("/info/{id}")
    public String Info(HttpServletResponse response,
                       HttpServletRequest request,
                       Model model,
                       @PathVariable(name = "id") Long id){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("part","main");
        if(id.equals(user.getId())){
            model.addAttribute("isme","yes");
        }else{
            model.addAttribute("isme","no");
        }
        return "information";
    }
    @GetMapping("/info/{id}/{part}")
    public String Info(HttpServletResponse response,
                       HttpServletRequest request,
                       Model model,
                       @PathVariable(name = "id") Long id,
                       @PathVariable(name="part",required = false) String part){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("part",part);
        if(id.equals(user.getId())){
            model.addAttribute("isme","yes");
        }else{
            model.addAttribute("isme","no");
        }
        return "information";
    }
}
