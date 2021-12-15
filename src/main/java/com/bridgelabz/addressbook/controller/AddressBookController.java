package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose: AddressBook RestController for RESTFul web services.
 *
 * @author Siraj
 * @version 1.0
 * @since 11-12-2021
 **/
@RestController
@RequestMapping("/addressbook/api")
public class AddressBookController {

    /**
     * Purpose : Autowiring to create instance of payroll service.
     */
    @Autowired
    private AddressBookService service;

    /**
     * Purpose : Welcome message to greet the user.
     *
     * @return Returns a string with welcome message.
     */
    @GetMapping("/hello")
    private String welcomeMessage() {
        return "Welcome to the address book";
    }

    /**
     * Purpose : Method to add new entry in the repository via POST.
     *
     * @return : Returns Response if the entry is successfully added.
     */
    @PostMapping(value = {"/add-entry"})
    public ResponseEntity<String> addEntry(
            @Valid @RequestBody AddressBookDto dto
    ) {
        return new ResponseEntity<>(service.addEntry(dto), HttpStatus.OK);
    }

    /**
     * Purpose : Method to get all the entry dto in the repository via GET.
     *
     * @return : Returns Response of list if the entry is successfully fetched
     */
    @GetMapping(value = {"get-all-entries"})
    public ResponseEntity<List<AddressBookDto>> getEntries() {
        return new ResponseEntity<>(service.getEntries(), HttpStatus.OK);
    }

    /**
     * Purpose : Method to update entry in the repository via PUT.
     *
     * @return : Returns Response if the entry is successfully updated
     */
    @PutMapping("/update-entry/{id}")
    public ResponseEntity<String> updateEntry(
            @PathVariable int id,
            @Valid @RequestBody AddressBookDto dto
    ) {
        return new ResponseEntity<>(service.updateEmployee(dto, id), HttpStatus.OK);
    }

    /**
     * Purpose : Method to delete new entry in the repository via DELETE.
     *
     * @return : Returns Response if the entry is successfully deleted
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(
            @PathVariable int id
    ) {
        return new ResponseEntity<>(service.deleteEntry(id), HttpStatus.OK);
    }
}
