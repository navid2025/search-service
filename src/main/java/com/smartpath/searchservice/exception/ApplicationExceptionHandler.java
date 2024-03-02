package com.smartpath.searchservice.exception;

import com.smartpath.searchservice.web.dto.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    //Todo: ExceptionHandler for downstream systems should be implemented
//    @ExceptionHandler(DownstreamException.class)
//    public ResponseEntity<BaseResponse> handleRuntimeException(DownstreamException ex, WebRequest request) {
//        BaseResponse baseResponse = BaseResponse.builder()
//                .resultCode("500")
//                .resultDesc("DownStream returns an exception ("+ ex.getMessage() + ")")
//                .build();
//        return new ResponseEntity(baseResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        BaseResponse baseResponse = BaseResponse.builder()
                .resultCode("500")
                .resultDesc("Internal Server Error")
                .build();
        return new ResponseEntity(baseResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
