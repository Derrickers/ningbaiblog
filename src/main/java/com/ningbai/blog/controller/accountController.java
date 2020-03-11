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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class accountController {

    @Autowired
    UserMapper userMapper;

    /**
     * 登陆/注册界面映射方法
     * @param model
     * @return
     */
    @GetMapping("/account")
    public String accountPage(Model model){
        model.addAttribute("method","login");
        return "account";
    }

    /**
     * 登陆/注册页面post表单逻辑处理
     * @param account 账户名
     * @param password  密码
     * @param duPass 第二次输入密码
     * @param method 方法：login/register
     * @param model 用于向页面添加属性
     * @param response 用户设置cookie
     * @return 跳转到对应页面
     */
    @PostMapping("/account")
    public String accountMethod(@RequestParam(value = "account") String account,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "duPass", required = false) String duPass,
                                @RequestParam(value = "method") String method,
                                Model model,
                                HttpServletResponse response){
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
                    //生成随机32位16的进制uuid
                    String uuid = UUID.randomUUID().toString();
                    //创建新cookie
                    Cookie cookie = new Cookie("token", uuid);
                    //设置cookie生存时间为 60s*60*24 = 1天
                    cookie.setMaxAge(60*60*24*2);
                    user.setToken(uuid);
                    //更新账户token
                    userExample.createCriteria().andAccountEqualTo(account);
                    //像浏览器添加cookie
                    response.addCookie(cookie);
                    userMapper.updateByExample(user,userExample);
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

    /**
     * 登出
     * @param request
     * @param response
     * @return 重定位到主界面
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        //清除session
        request.getSession().removeAttribute("user");
        //设置一个新的Cookie，生存时间为0
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
