package kr.co.fortice.blog.exception.custom;

public class UnauthenticatedException extends RuntimeException{
    private String message;

    public UnauthenticatedException(String message) {
        this.message = message;
    }
}
