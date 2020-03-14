package com.ningbai.blog.cache;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TagCache {
    private ArrayList<String> emotion = new ArrayList<>();
    private ArrayList<String> study = new ArrayList<>();
    private ArrayList<String> life = new ArrayList<>();
    private ArrayList<String> surprise = new ArrayList<>();

    public TagCache(){
        InitTagCache();
    }

    public void InitTagCache(){
        InitEmotion();
        InitSurprise();
        InitStudy();
        InitLife();
    }

    private void InitStudy(){
        study.add("编程");
        study.add("数学");
        study.add("体育");
        study.add("英语");
    }
    private void InitEmotion(){
        emotion.add("想你");
        emotion.add("爱你");
        emotion.add("吃掉你");
    }
    private void InitLife(){
        life.add("做饭");
        life.add("运动");
        life.add("买可乐");
    }
    private void InitSurprise(){
        surprise.add("生日");
        surprise.add("节日");
        surprise.add("纪念日");
        surprise.add("没啥意义就是想送");
    }

}
