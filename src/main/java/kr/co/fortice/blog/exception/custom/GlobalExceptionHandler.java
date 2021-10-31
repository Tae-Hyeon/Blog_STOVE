package kr.co.fortice.blog.exception.custom;

import kr.co.fortice.blog.dto.response.ExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = {Controller.class, Service.class})
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e) {
        e.printStackTrace();
        return ExceptionResponse.toResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                "에러가 발생했습니다."
        );
    }
}
