package com.ningbai.blog.service;


import com.ningbai.blog.DTO.BlogDTO;
import com.ningbai.blog.DTO.PaginationDTO;
import com.ningbai.blog.mapper.BlogMapper;
import com.ningbai.blog.mapper.UserMapper;
import com.ningbai.blog.model.Blog;
import com.ningbai.blog.model.BlogExample;
import com.ningbai.blog.model.User;
import com.ningbai.blog.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO<BlogDTO> getBlogs(int page, Integer size){
        BlogExample blogExample = new BlogExample();
        long totalCount = blogMapper.countByExample(blogExample);
        int offset = (page-1)*size;
        PaginationDTO<BlogDTO> paginationDTO = new PaginationDTO<>();
        ArrayList<BlogDTO> blogDTOs = new ArrayList<>();
        paginationDTO.setPagination(totalCount,(long)page,size);
        blogExample.setOrderByClause("`gmt_create` desc");
        List<Blog> bloglist = blogMapper.selectByExampleWithRowbounds(blogExample, new RowBounds(offset, size));
        User nullUser = new User();
        nullUser.setName("用户已注销");
        for(Blog blog:bloglist){
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setBlog(blog);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountEqualTo(blog.getAuthor());
            List<User> users = userMapper.selectByExample(userExample);
            User user = (users.size() == 0)?(nullUser):(users.get(0));
            blogDTO.setUser(user);
            blogDTOs.add(blogDTO);
        }
        paginationDTO.setData(blogDTOs);
        return paginationDTO;
    }
}
