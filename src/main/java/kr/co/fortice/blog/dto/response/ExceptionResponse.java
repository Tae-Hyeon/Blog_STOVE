package kr.co.fortice.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ExceptionResponse {
    private String error;
    private String message;

    public static ResponseEntity<ExceptionResponse> toResponseEntity(int status, String error, String message) {
        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .error(error)
                        .message(message)
                        .build()
                );
    }
}
