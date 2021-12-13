package com.bridgelabz.addressbook.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
