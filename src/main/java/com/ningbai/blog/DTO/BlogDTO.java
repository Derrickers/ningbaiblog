package com.ningbai.blog.DTO;

import com.ningbai.blog.model.Blog;
import com.ningbai.blog.model.User;
import lombok.Data;

@Data
public class BlogDTO {
    private Blog blog;
    private User user;
    private String[] tags;

    public void setTags(String tag){
        tags = tag.split("/");
    }
}
