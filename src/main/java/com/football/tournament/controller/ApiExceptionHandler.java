
package com.football.tournament.controller;


import com.football.tournament.data.ErrorResponse;
import com.football.tournament.exception.CustomException;
import com.football.tournament.exception.InvalidOperationException;
import com.football.tournament.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * This contains all exception-handling logic.
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.football.tournament")
public class ApiExceptionHandler
{

    private static final String DEFAULT_ERROR_MESSAGE = "An error has occurred";
    private static final String REQUEST_BODY_ERROR_MESSAGE = "Request Body doesn't match API specification";

    private String getMessageFromError(Exception e, String defaultMessage)
    {
        if (e.getMessage() == null)
        {
            return defaultMessage;
        }
        else
        {
            return e.getLocalizedMessage();
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleArgumentTypeMismatch(MethodArgumentTypeMismatchException e)
    {
        log.warn("Method argument type mismatch: {}. " + e.getMessage(), e.getName());

        String message = String.format("Invalid format of argument: %s", e.getName());

        return getError("ArgumentMismatch", message,
                HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidEncoding(UnsupportedEncodingException e)
    {
        log.warn("Invalid encoding detected: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "Invalid encoding detected");
        return getError("UnsupportedCodingException", message, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleIOException(IOException e)
    {
        log.error("Encountered IO exception: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "Encountered IO Exception");
        return getError("IOException", message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadArgumentException(IllegalArgumentException e)
    {
        log.warn("Encountered illegal argument exception: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "Encountered illegal argument");
        return getError("IllegalArgumentException", message, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e)
    {
        log.warn("Resource not found: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "A specified resource was not found");
        return getError("ResourceNotFoundException", message, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidOperationException(InvalidOperationException e)
    {
        log.warn("Invalid operation: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "Invalid Operation");
        return getError("InvalidOperationException", message + " " + e.getReason(),
                HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleRequireHeadersException(ServletRequestBindingException e)
    {
        log.warn("Required header missing: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "Required header missing");

        return getError("MissingRequestHeaderException", message, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleCustomException(CustomException e)
    {
        log.warn("Encountered bad request: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "Bad Request");
        return getError("Error", message, HttpStatus.BAD_REQUEST.value());

    }

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleRestClientException(RestClientException e)
    {
        log.warn("Encountered rest client exception: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "Bad Request");
        return getError("FailedHttpRequest", message, HttpStatus.BAD_REQUEST.value());

    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e)
    {
        log.warn("Encountered HttpRequestMethodNotSupportedException: {}", e.getMessage(), e);

        String message = getMessageFromError(e, "HttpRequestMethodNotSupported Exception");
        return getError("HttpRequestMethodNotSupportedException",
                message, HttpStatus.BAD_REQUEST.value());
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleAllOtherExceptions(Exception e)
    {
        log.warn("Unhandled exception: {}", e.getMessage(), e);

        return getError("InternalServerException", DEFAULT_ERROR_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e)
    {
        log.warn("ErrorResponse in request body : {}", e.getMessage());
        return getError("ErrorResponse in request body", REQUEST_BODY_ERROR_MESSAGE, BAD_REQUEST.value());
    }


    private ErrorResponse getError(String error, String message, int code)
    {
        return ErrorResponse.builder()
                .error(error)
                .message(message)
                .code(code)
                .createdAt(OffsetDateTime.now())
                .build();
    }
}
