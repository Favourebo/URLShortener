package com.carbon.urlshortener.api;

import com.carbon.urlshortener.exceptions.BaseException;
import com.carbon.urlshortener.exceptions.InvalidIDException;
import com.carbon.urlshortener.exceptions.UnableToSaveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@RestController
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processBaseException(BaseException ex) {

        log.error("Controller Advice is being called. Base exception thrown", ex);
        return new ApiResponse(new ErrorResponse(ex.getMessage()));
    }


    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse nullPointerException(NullPointerException ex) {
        log.error("Controller Advice is being called. Base exception thrown", ex);
        return new ApiResponse(new ErrorResponse("Invalid request!"));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getDefaultMessage())
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ApiResponse(new ErrorResponse(errorMessages.toString())));
    }

    @ExceptionHandler(InvalidIDException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse invalidIDException(InvalidIDException ex) {

        log.error("Controller Advice is being called. update failed exception thrown", ex);
        return new ApiResponse(new ErrorResponse(ex.getMessage()));
    }


    @ExceptionHandler(UnableToSaveException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse unableToSaveException(UnableToSaveException ex) {

        log.error("Controller Advice is being called. unable to save exception thrown", ex);
        return new ApiResponse(new ErrorResponse(ex.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse processException(Exception ex) {

        log.error("Controller Advice is being called. Exception thrown", ex);
        return new ApiResponse(new ErrorResponse("Unable to complete request."));
    }
}
