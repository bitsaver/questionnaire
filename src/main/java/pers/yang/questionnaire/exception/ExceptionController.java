package pers.yang.questionnaire.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.utils.ResponseUtil;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Response exceptionHandler(RuntimeException re){
        System.err.println("exceptionHandler");
        re.printStackTrace();
        if (re instanceof CustomException) {
            return ResponseUtil.error((CustomException)re);
        }else if (re instanceof UnauthenticatedException){
            return ResponseUtil.error(ErrorType.NO_LOGIN);
        }else if(re instanceof UnauthorizedException){
            return ResponseUtil.error(ErrorType.UNAUTHORIZED);
        }
        return ResponseUtil.error(ErrorType.UNKNOWN_ERROR);
    }
}
