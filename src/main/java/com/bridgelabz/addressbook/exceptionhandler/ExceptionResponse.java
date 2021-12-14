package com.bridgelabz.addressbook.exceptionhandler;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Purpose: Error object to carry data from exception..
 *
 * @version 1.0
 * @since 07-12-2021
 **/
@Data
public class ExceptionResponse {

    private Date timestamp;
    private int status;
    private List<String> error;
}