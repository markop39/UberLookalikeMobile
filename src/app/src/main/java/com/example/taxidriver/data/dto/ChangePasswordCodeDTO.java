package com.example.taxidriver.data.dto;

public class ChangePasswordCodeDTO {


    private String email;
    private String new_password;
    private String code;

    public ChangePasswordCodeDTO(){
    }

    public ChangePasswordCodeDTO(String email, String new_password, String code) {
        this.email = email;
        this.new_password = new_password;
        this.code = code;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
