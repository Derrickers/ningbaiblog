package com.ningbai.blog.controller;

import com.ningbai.blog.mapper.UserMapper;
import com.ningbai.blog.model.User;
import com.ningbai.blog.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class accountController {

    @Autowired
    UserMapper userMapper;

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
        UserExample userExample = new UserExample();
        model.addAttribute("account",account);
        if(method.equals("login")){
            model.addAttribute("method","login");
            userExample.createCriteria()
                    .andAccountEqualTo(account);
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size()==0){
                model.addAttribute("error","用户不存在呢，是不是输错了呀～");
            }else{
                User user = users.get(0);
                if(!user.getPassword().equals(password)){
                    model.addAttribute("error","密码错了诶～小傻瓜是不是记错了呀");
                }else{
                    return "redirect:/";
                }
            }
            return "account";
        }else if(method.equals("register")){
            //判断是否注册过
            userExample.createCriteria()
                    .andAccountEqualTo(account);
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size()!=0){
                model.addAttribute("error","诶，这个邮箱已经注册过了，换一个吧～（不会是被盗用了吧）");
                model.addAttribute("method",method);
                return "account";
            }
            //处理密码逻辑
            if(!password.equals(duPass)){
                model.addAttribute("error","两次密码不一样哦～重新写一下吧");
                model.addAttribute("method",method);
            }else{
                User user = new User();
                user.setAccount(account);
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModify(user.getGmtCreate());
                user.setLabel("");
                user.setPassword(password);
                userMapper.insert(user);
                model.addAttribute("success","注册成功啦，快用刚刚注册好的账号登陆吧～");
                model.addAttribute("method","login");
            }
            return "account";
        }
        return "redirect:/";
    }

}
