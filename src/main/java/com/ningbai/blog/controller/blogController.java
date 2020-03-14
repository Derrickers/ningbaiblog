package com.ningbai.blog.controller;

import com.ningbai.blog.DTO.BlogDTO;
import com.ningbai.blog.model.User;
import com.ningbai.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class blogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{id}")
    public String readBlog(@PathVariable(value = "id") Long id,
                           Model model,
                           HttpServletRequest request){
        BlogDTO blogDTO = blogService.getBlog(id);
        User user = (User)request.getSession().getAttribute("user");
        if(user.getAccount().equals(blogDTO.getUser().getAccount())){
            model.addAttribute("modify","true");
        }else{
            model.addAttribute("modify","false");
        }
        String[] tags = blogDTO.getBlog().getTags().split("/");
        model.addAttribute("tags", tags);
        model.addAttribute("blog",blogDTO);
        return "blog";
    }
}
