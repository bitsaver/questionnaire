package pers.yang.questionnaire.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Response implements Serializable {
    
    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    public Object data;

}