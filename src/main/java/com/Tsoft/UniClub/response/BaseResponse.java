package com.Tsoft.UniClub.response;


import lombok.Data;

@Data
public class BaseResponse {
    private int statusCode =200;
    private String message;
    private Object data;
}
