package com.ningbai.blog.controller;

import com.ningbai.blog.cache.TagCache;
import com.ningbai.blog.mapper.BlogMapper;
import com.ningbai.blog.model.Blog;
import com.ningbai.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class publishController {

    private TagCache tagCache = new TagCache();

    @Autowired
    private BlogMapper blogMapper;

    private void addTags(Model model){
        model.addAttribute("emotionTags",tagCache.getEmotion());
        model.addAttribute("lifeTags",tagCache.getLife());
        model.addAttribute("studyTags",tagCache.getStudy());
        model.addAttribute("surpriseTags",tagCache.getSurprise());
    }

    @GetMapping("/publish")
    public String publishPage(Model model){
        addTags(model);
        return "publish";
    }


    @PostMapping("/publish")
    public String publish(HttpServletRequest request,
                          HttpServletResponse response,
                          Model model,
                          @RequestParam(name = "title") String title,
                          @RequestParam(name = "content") String content,
                          @RequestParam(name = "tags") String tags){
        addTags(model);
        model.addAttribute("title",title);
        model.addAttribute("content",content);
        model.addAttribute("tags",tags);
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","请先登陆");
            return "publish";
        }
        if(title == null||title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(content == null||content.equals("")){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        Blog blog = new Blog();
        blog.setAuthor(user.getAccount());
        blog.setTitle(title);
        blog.setContent(content);
        blog.setGmtCreate(System.currentTimeMillis());
        blog.setGmtModify(blog.getGmtCreate());
        blog.setTags(tags);
        blogMapper.insert(blog);
        return "redirect:/";
    }

}
