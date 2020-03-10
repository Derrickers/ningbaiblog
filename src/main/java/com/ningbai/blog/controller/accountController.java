package com.ningbai.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class accountController {

    @GetMapping("/account")
    public String accountPage(Model model){
        model.addAttribute("method","login");
        return "account";
    }

    @PostMapping("/account")
    public String accountMethod(@RequestParam(value = "account") String account,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "duPass",required = false) String duPass,
                                @RequestParam(value = "method") String method,
                                Model model){
        model.addAttribute("account",account);
        if(method.equals("login")){
            return "account";
        }else if(method.equals("register")){
            if(!password.equals(duPass)){
                model.addAttribute("error","两次密码不一样哦～重新写一下吧");
                model.addAttribute("method",method);
            }else{
                model.addAttribute("success","注册成功啦，快用刚刚注册好的账号登陆吧～");
                model.addAttribute("method","login");
            }
            return "account";
        }
        return "redirect:/";
    }

}
