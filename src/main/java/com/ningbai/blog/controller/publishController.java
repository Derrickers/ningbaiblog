package com.ningbai.blog.controller;

import com.ningbai.blog.cache.HelpCache;
import com.ningbai.blog.cache.TagCache;
import com.ningbai.blog.exception.MyException;
import com.ningbai.blog.exception.UserErrorCode;
import com.ningbai.blog.mapper.BlogMapper;
import com.ningbai.blog.model.Blog;
import com.ningbai.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    private TagCache tagCache = new TagCache();
    private HelpCache helpCache = new HelpCache();

    @Autowired
    private BlogMapper blogMapper;

    private void addAttribute(Model model){
        model.addAttribute("emotionTags",tagCache.getEmotion());
        model.addAttribute("lifeTags",tagCache.getLife());
        model.addAttribute("studyTags",tagCache.getStudy());
        model.addAttribute("surpriseTags",tagCache.getSurprise());
        model.addAttribute("markdownhelp",helpCache.getMarkdownHelp());
    }

    @GetMapping("/publish")
    public String publishPage(Model model){
        addAttribute(model);
        return "publish";
    }


    @PostMapping("/publish")
    public String publish(HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "title", required = false) String title,
                          @RequestParam(name = "content", required = false) String content,
                          @RequestParam(name = "tags", required = false) String tags,
                          @RequestParam(name = "id", required = false) Long id){
        addAttribute(model);
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
        if(id == null){
            blog.setAuthor(user.getAccount());
            blog.setTitle(title);
            blog.setContent(content);
            blog.setGmtCreate(System.currentTimeMillis());
            blog.setGmtModify(blog.getGmtCreate());
            blog.setTags(tags);
            blog.setViewCount((long) 0);
            blog.setLikeCount((long) 0);
            blogMapper.insert(blog);
        }else{
            blog = blogMapper.selectByPrimaryKey(id);
            blog.setGmtModify(System.currentTimeMillis());
            blog.setTitle(title);
            blog.setContent(content);
            blog.setTags(tags);
            blogMapper.updateByPrimaryKey(blog);
        }
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String modify(@PathVariable(value = "id") long id,
                         Model model,
                         HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        Blog blog = blogMapper.selectByPrimaryKey(id);
        if(!user.getAccount().equals(blog.getAuthor())){
            throw new MyException(UserErrorCode.USER_NOT_MATCH);
        }
        model.addAttribute("title",blog.getTitle());
        model.addAttribute("content",blog.getContent());
        model.addAttribute("tags",blog.getTags());
        model.addAttribute("id",id);
        return "publish";
    }

}
