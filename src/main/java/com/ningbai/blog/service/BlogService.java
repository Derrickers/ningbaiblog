package com.ningbai.blog.service;


import com.ningbai.blog.DTO.BlogDTO;
import com.ningbai.blog.DTO.PaginationDTO;
import com.ningbai.blog.exception.BlogErrorCode;
import com.ningbai.blog.exception.MyException;
import com.ningbai.blog.mapper.BlogMapper;
import com.ningbai.blog.mapper.RecordMapper;
import com.ningbai.blog.mapper.UserMapper;
import com.ningbai.blog.model.*;
import com.ningbai.blog.typeEnum.ReceiverType;
import com.ningbai.blog.typeEnum.RecordType;
import com.ningbai.blog.typeEnum.StateType;
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

    @Autowired
    private RecordMapper recordMapper;

    public PaginationDTO<BlogDTO> getBlogList(int page, Integer size){
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

    public BlogDTO getBlog(Long id){
        Blog blog = blogMapper.selectByPrimaryKey(id);
        if(blog == null){
            throw new MyException(BlogErrorCode.BLOG_NOT_FOUND);
        }
        blog.setViewCount(blog.getViewCount()+1);
        blogMapper.updateByPrimaryKey(blog);
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setBlog(blog);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(blogDTO.getBlog().getAuthor());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0){
            User user = new User();
            user.setId((long) -1);
            user.setName("用户已注销");
            user.setAccount("-1");
            blogDTO.setUser(user);
        }else{
            blogDTO.setUser(users.get(0));
        }
        blogDTO.setTags(blog.getTags());
        return blogDTO;
    }

    public PaginationDTO<BlogDTO> getBlogByAccount(String account, int page, int size) {
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andAuthorEqualTo(account);
        long totalCount = blogMapper.countByExample(blogExample);
        blogExample.setOrderByClause("`gmt_create` desc");
        int offset = (page-1)*size;
        PaginationDTO<BlogDTO> paginationDTO = new PaginationDTO<>();
        ArrayList<BlogDTO> blogDTOs = new ArrayList<>();
        paginationDTO.setPagination(totalCount,(long)page,size);
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

    public void deleteById(long id) {
        blogMapper.deleteByPrimaryKey(id);
    }

    public void likeBlog(long blogId,long userId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        blog.setLikeCount(blog.getLikeCount()+1);
        blogMapper.updateByPrimaryKey(blog);
        Record record = new Record();
        record.setCreator(userId);
        record.setType(RecordType.LIKE.getType());
        record.setReceiver(blogId);
        record.setReceiverType(ReceiverType.BLOG.getType());
        record.setGmtCreate(System.currentTimeMillis());
        record.setState(StateType.UNREAD.getType());
        recordMapper.insert(record);
    }
}
