package com.thedebuggers.backend.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("ErrorResponseBody")
public class ErrorResponseBody {
    @ApiModelProperty(name="응답 메시지", example = "응답 내용")
    String message;
    @ApiModelProperty(name="에러 클래스", example = "class java.lang.NullPointerException")
    Class<? extends Exception> exception;

    public static ErrorResponseBody of(String message, Class<? extends Exception> exception) {
        return ErrorResponseBody.builder()
                .message(message)
                .exception(exception)
                .build();
    }
}
