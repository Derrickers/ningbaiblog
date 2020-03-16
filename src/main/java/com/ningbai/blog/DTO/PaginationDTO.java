package com.ningbai.blog.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PaginationDTO<T> {
    private ArrayList<T> data;
    private long totalPage;
    private boolean first;
    private boolean before;
    private boolean next;
    private boolean end;

    private long page;
    private ArrayList<Long> pages = new ArrayList<>();
    public void setPagination(Long totalCount,Long page,Integer size){
        if(totalCount == 0){
            this.page = 1;
            pages.add((long) 1);
            first = false;
            before = false;
            next = false;
            end = false;
            return ;
        }
        this.totalPage = (totalCount%size ==0)?(totalCount/size):(totalCount/size+1);
        if(page>totalPage)
            this.page = totalPage;
        else if(page<=0)
            this.page = 1;
        else
            this.page = page;
        pages.add(this.page);
        for(int i = 1;i<=3;i++){
            if(page-i>0)
                pages.add(0,page-i);
            if(page+i<=this.totalPage)
                pages.add(page+i);
        }
        first = !pages.contains((long)1);
        before = !(this.page == 1);
        next = !(this.page == totalPage);
        end = !(pages.contains(totalPage));
    }
}
