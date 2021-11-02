package kr.co.fortice.blog.exception.custom;

import lombok.Getter;

@Getter
public class AlreadySignedUpEmailException extends RuntimeException{
    private String message;
    public AlreadySignedUpEmailException(String message) {
        this.message = message;
    }
}
