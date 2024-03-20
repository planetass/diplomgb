package ru.vss.userservice.exaptions;

public class UserException extends RuntimeException{
    private String msg;

    public UserException(String s) {
        this.msg=s;
    }

    @Override
    public String getMessage() {
        return super.getMessage()+msg;
    }
}
