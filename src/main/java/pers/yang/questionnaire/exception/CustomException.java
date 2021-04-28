package pers.yang.questionnaire.exception;


public class CustomException extends RuntimeException{
    private Integer code;

    public CustomException(ErrorType type){
        super(type.getMsg());
        this.code = type.getCode();
    }

    public CustomException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}