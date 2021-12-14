package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping("/hello")
    private String welcomeMessage() {
        return "Welcome to the address book";
    }

    @PostMapping(value = {"/add-entry"})
    public ResponseEntity<String> addEmp(
            @Valid @RequestBody AddressBookDto dto
    ) {
        return new ResponseEntity<>(service.addEntry(dto), HttpStatus.OK);
    }

    @GetMapping(value = {"get-all-entries"})
    public ResponseEntity<List<AddressBookDto>> getAllEmployees() {
        return new ResponseEntity<>(service.getEntries(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id
    ) {
        return new ResponseEntity<>(service.deleteEntry(id), HttpStatus.OK);
    }

    @PutMapping("/update-entry/{id}")
    public ResponseEntity<String> updateEntry(
            @PathVariable int id,
            @Valid @RequestBody AddressBookDto dto
    ) {
        return new ResponseEntity<>(service.updateEmployee(dto, id), HttpStatus.OK);
    }
}
