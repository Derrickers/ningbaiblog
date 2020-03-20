package com.ningbai.blog.controller;

import com.ningbai.blog.DTO.BlogDTO;
import com.ningbai.blog.DTO.PaginationDTO;
import com.ningbai.blog.exception.MyException;
import com.ningbai.blog.exception.UserErrorCode;
import com.ningbai.blog.mapper.BlogMapper;
import com.ningbai.blog.mapper.UserMapper;
import com.ningbai.blog.model.Blog;
import com.ningbai.blog.model.User;
import com.ningbai.blog.model.UserExample;
import com.ningbai.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class informationController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/info/{id}")
    public String Info(HttpServletResponse response,
                       HttpServletRequest request,
                       Model model,
                       @PathVariable(name = "id") Long id){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("part","main");
        User pageUser = judgeUser(model, id, user);
        return "information";
    }

    private User judgeUser(Model model, Long id, User user) {
        if(id.equals(user.getId())){
            model.addAttribute("user",user);
            model.addAttribute("isme","yes");
        }else{
            user = userMapper.selectByPrimaryKey(id);
            if(user ==  null){
                throw new MyException(UserErrorCode.USER_NOT_FOUNT);
            }
            model.addAttribute("user", user);
            model.addAttribute("isme","no");
        }
        return user;
    }

    @GetMapping("/info/{id}/{part}")
    public String Info(HttpServletResponse response,
                       HttpServletRequest request,
                       Model model,
                       @PathVariable(name = "id") Long id,
                       @PathVariable(name="part",required = false) String part,
                       @RequestParam(name = "page",defaultValue = "1") int page,
                       @RequestParam(name = "size",defaultValue = "1") int size){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("part",part);
        User pageUser = judgeUser(model, id, user);
        if(part.equals("blogs")){
            PaginationDTO<BlogDTO> paginationDTO = blogService.getBlogByAccount(pageUser.getAccount(), page, size);
            model.addAttribute("pagination",paginationDTO);
        }
        return "information";
    }

    @PostMapping("/info/{id}/modify")
    public String ModifyInfo(@PathVariable(name = "id") Long id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "sex") Integer sex,
                             @RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "description") String description,
                             HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        user.setName(name);
        user.setDescription(description);
        user.setGmtModify(System.currentTimeMillis());
        user.setPassword(password);
        user.setAccount(account);
        user.setSex(sex);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        userMapper.updateByExample(user,userExample);
        return "redirect:/info/"+id.toString();
    }
}
