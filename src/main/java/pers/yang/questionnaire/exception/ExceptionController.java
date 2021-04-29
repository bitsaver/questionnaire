package pers.yang.questionnaire.exception;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.utils.ResponseUtil;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception re){
        System.err.println("====> exceptionHandler");
        re.printStackTrace();
        if (re instanceof CustomException) {
            return ResponseUtil.error((CustomException)re);
        }else if (re instanceof UnauthenticatedException){
            return ResponseUtil.error(ErrorType.NO_LOGIN);
        }else if(re instanceof UnauthorizedException){
            return ResponseUtil.error(ErrorType.UNAUTHORIZED);
        }else if(re instanceof IncorrectCredentialsException) {
            return ResponseUtil.error(ErrorType.PASSWORD_INCORRECT);
        }else if(re instanceof AuthenticationException){
            return ResponseUtil.error(ErrorType.USER_NAME_INCORRECT);
        }else if(re instanceof MessagingException){
            return ResponseUtil.error(ErrorType.FAIL_SENDING_EMAIL);
        }else if(re instanceof MissingServletRequestParameterException){
            return ResponseUtil.error(ErrorType.INPUT_INCOMPLETE);
        }
        return ResponseUtil.error(ErrorType.UNKNOWN_ERROR);
    }
}
