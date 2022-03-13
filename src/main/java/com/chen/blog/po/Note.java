package com.chen.blog.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Note {

    private Integer noteId; //云记Id
    private String title; //云记标题
    private String content; //云记内容
    private Integer typeId; //云记类型ID
    private Date pubTime; //发布时间
    private String typeName; //类型名称
    private  Float lon; //经度
    private Float lat; //维度
}
