package com.javaTraining.nobug.exception;

public class CustomException extends RuntimeException{
    private long code;
    private String msg;

    public CustomException(Long code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getMessage(){
        return getMsg();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
