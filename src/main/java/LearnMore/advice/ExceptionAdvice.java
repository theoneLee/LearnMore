package LearnMore.advice;

import LearnMore.entity.Response;
import LearnMore.security.TokenException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * 全局的异常处理切面类，用它来统一处理所有的异常行为
 * Created by Lee on 2017/5/7 0007.
 */
@ControllerAdvice
//该注解可声明全局的异常处理切面类，用它来统一处理所有的异常行为（内部实现：把@ExceptionHandler注解的方法应用到所有@RequestMapping注解的方法中，即可以把该异常处理器应用到所有controller中）
@ResponseBody//表示返回值可序列化为 JSON 字符串
public class ExceptionAdvice {
    /**
     * 400 - Bad Request 参数解析失败
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return new Response().failure("could_not_read_json");
    }

    /**
     * 400 - Bad Request 缺少请求参数
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        e.printStackTrace();
        return new Response().failure("required_parameter_is_not_present");
    }

    /**
     * 401 - Unauthorized 令牌验证失败
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenException.class)
    public Response handleTokenException(TokenException e) {
        e.printStackTrace();
        return new Response().failure("token_exception");
    }

    /**
     * 405 - Method Not Allowed 不支持当前请求方法
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return new Response().failure("request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type 不支持当前媒体类型
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response handleHttpMediaTypeNotSupportedException(Exception e) {
        e.printStackTrace();
        return new Response().failure("content_type_not_supported");
    }

    /**
     * 500 - Internal Server Error 服务运行异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public Response handleServiceException(ServiceException e) {
        e.printStackTrace();
        return new Response().failure(e.getMessage());
    }

    /**
     * 500 - Internal Server Error IO异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public Response handleException(IOException e) {
        e.printStackTrace();
        return new Response().failure(e.getMessage());
    }
    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        e.printStackTrace();
        return new Response().failure(e.getMessage());
    }


}