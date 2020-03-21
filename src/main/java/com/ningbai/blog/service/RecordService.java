package com.ningbai.blog.service;

import com.ningbai.blog.DTO.BlogDTO;
import com.ningbai.blog.DTO.BlogPageDTO;
import com.ningbai.blog.typeEnum.RecordType;
import com.ningbai.blog.mapper.BlogMapper;
import com.ningbai.blog.mapper.RecordMapper;
import com.ningbai.blog.model.Record;
import com.ningbai.blog.model.RecordExample;
import com.ningbai.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private BlogMapper blogMapper;

    public BlogPageDTO setBlogPageDTO(User user, BlogDTO blogDTO) {
        Long blogId = blogDTO.getBlog().getId();
        BlogPageDTO blogPageDTO = new BlogPageDTO();
        if(blogDTO.getUser().getId().equals(user.getId())){
            blogPageDTO.setModifiable(true);
        }else{
            blogPageDTO.setModifiable(false);
        }
        RecordExample recordExample = new RecordExample();
        recordExample.createCriteria().andCreatorEqualTo(user.getId());
        List<Record> records = recordMapper.selectByExample(recordExample);
        blogPageDTO.setLikable(true);
        List<Record> recordList = records.stream().filter(record -> record.getReceiver().equals(blogId))
                .collect(Collectors.toList());
        if(recordList.size()!=0){
            for(Record record:recordList){
                if(record.getReceiver().equals(blogId)) {
                    if(record.getType()== RecordType.LIKE.getType())
                        blogPageDTO.setLikable(false);
                    else if(record.getType()== RecordType.COLLECT.getType())
                        blogPageDTO.setCollectible(false);
                }
            }
        }
        return blogPageDTO;
    }

    public boolean hasRecord(long blogId, long userId,int type,int receiverType) {
        RecordExample recordExample = new RecordExample();
        recordExample.createCriteria()
                .andCreatorEqualTo(userId)
                .andReceiverEqualTo(blogId)
                .andTypeEqualTo(type)
                .andReceiverTypeEqualTo(receiverType);
        List<Record> records = recordMapper.selectByExample(recordExample);
        return records.size() == 0;
    }
}
