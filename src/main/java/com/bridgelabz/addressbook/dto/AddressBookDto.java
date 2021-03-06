package com.bridgelabz.addressbook.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Purpose: AddressBook DTO to carry data between processes.
 *
 * @author Siraj
 * @version 1.0
 * @since 05-12-2021
 **/
@Data
public class AddressBookDto {

    private int id;

    @NotNull
    @Pattern(regexp = "[A-Za-z ]{3,32}", message = "Name should not contain any number and must be at least 3 characters in length.")
    private String name;

    @Pattern(regexp = "[A-Za-z0-9 ]{3,100}", message = "Address should not be longer than 100 characters")
    private String address;

    @Pattern(regexp = "[A-Za-z ]{3,32}", message = "City should not contain any number and must be at least 3 characters in length.")
    private String city;

    @Pattern(regexp = "[A-Za-z ]{3,32}", message = "State should not contain any number and must be at least 3 characters in length.")
    private String state;

    @Pattern(regexp = "[0-9]{10}", message = "Phone numbers should be exactly 10 digits.")
    private String phoneNumber;

    private int zip;
}
