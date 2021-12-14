package com.bridgelabz.addressbook.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Purpose: AddressBook entity to represents a table in a database
 *
 * @author Siraj
 * @version 1.0
 * @since 11-12-2021
 **/
@Entity
@Data
@Table(name = "addressbook")
public class AddressBookEntity {

    @Id
    @Column(name = "address_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "zip")
    private int zip;
}
