package edu.whu.demo.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private int code; //建议定义成枚举类型
    private String message;
    private LocalDateTime dateTime;

}
