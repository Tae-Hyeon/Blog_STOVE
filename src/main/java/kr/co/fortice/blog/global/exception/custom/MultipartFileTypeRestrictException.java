package kr.co.fortice.blog.global.exception.custom;

import lombok.Getter;

@Getter
public class MultipartFileTypeRestrictException extends RuntimeException {
    private String message;

    public MultipartFileTypeRestrictException() {
        this.message = "파일 타입이 이미지가 아닙니다.";
    }
}
