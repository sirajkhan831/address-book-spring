package com.bridgelabz.addressbook.exceptionhandler;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ErrorObject {

    private Date timestamp;
    private int status;
    private List<String> error;
}
