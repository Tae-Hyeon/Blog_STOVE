package kr.co.fortice.blog.global.exception.custom;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException{
    private String message;

    public DataNotFoundException() {
        this.message = "찾는 내용이 없습니다.";
    }
}
