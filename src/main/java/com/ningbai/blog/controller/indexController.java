package com.ningbai.blog.controller;

import com.ningbai.blog.DTO.BlogDTO;
import com.ningbai.blog.DTO.PaginationDTO;
import com.ningbai.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String indexPage(@RequestParam(name = "page",defaultValue = "1") Integer page,
                            @RequestParam(name = "size",defaultValue = "8") Integer size,
                            Model model){
        PaginationDTO<BlogDTO> paginationDTO = blogService.getBlogList(page, size);
        model.addAttribute("pagination",paginationDTO);
        return "index";
    }

}
