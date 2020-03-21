package com.ningbai.blog.controller;

import com.ningbai.blog.DTO.BlogDTO;
import com.ningbai.blog.DTO.BlogPageDTO;
import com.ningbai.blog.exception.MyException;
import com.ningbai.blog.exception.UserErrorCode;
import com.ningbai.blog.model.User;
import com.ningbai.blog.service.BlogService;
import com.ningbai.blog.service.RecordService;
import com.ningbai.blog.typeEnum.ReceiverType;
import com.ningbai.blog.typeEnum.RecordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class blogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/blog/{id}")
    public String readBlog(@PathVariable(value = "id") Long id,
                           Model model,
                           HttpServletRequest request){
        BlogDTO blogDTO = blogService.getBlog(id);
        User user = (User)request.getSession().getAttribute("user");
        BlogPageDTO blogPageDTO = recordService.setBlogPageDTO(user,blogDTO);
        model.addAttribute("blogPage",blogPageDTO);
        model.addAttribute("blog",blogDTO);
        return "blog";
    }

    @GetMapping("/blog/delete/{id}/{page}")
    public String deleteBlog(@PathVariable(value = "id") long id,
                             @PathVariable(value = "page") String page,
                             HttpServletRequest request){
        User pageUser = (User) request.getSession().getAttribute("user");
        BlogDTO blogDTO = blogService.getBlog(id);
        if(!pageUser.getAccount().equals(blogDTO.getUser().getAccount())){
            throw new MyException(UserErrorCode.USER_NOT_MATCH);
        }
        blogService.deleteById(id);
        if(page.equals("info")){
            return "redirect:/info/"+pageUser.getId()+"/blogs";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/blog/like")
    public String likeBlog(@RequestParam(name = "blog") long blogId,
                           @RequestParam(name = "user") long userId){
        if(recordService.hasRecord(blogId,userId, RecordType.LIKE.getType(), ReceiverType.BLOG.getType())){
            blogService.likeBlog(blogId,userId);
        }
        return "redirect:/blog/"+blogId;
    }

}
