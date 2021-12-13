package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressBookController {

    private final Logger logger = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    private AddressBookService service;

    @GetMapping("/hello")
    private String welcomeMessage() {
        logger.error("ERROR");
        return "Welcome to the address book";
    }

    @PostMapping(value = {"/add-entry"})
    public ResponseEntity<String> addEmp(
            @Valid @RequestBody AddressBookDto dto
    ) {
        logger.error("ERROR");
        return new ResponseEntity<>(service.addEmp(dto), HttpStatus.OK);
    }

    @GetMapping(value = {"get-all-entries"})
    public List<AddressBookDto> getAllEmployees() {
        logger.error("ERROR");
        return service.getEntries();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id
    ) {
        logger.error("ERROR");
        return new ResponseEntity<>(service.deleteEntry(id), HttpStatus.OK);
    }

    @PutMapping("/update-entry")
    public ResponseEntity<String> updateEntry(
            @Valid @RequestBody AddressBookDto dto
    ) {
        logger.error("ERROR");
        return new ResponseEntity<>(service.updateEmployee(dto), HttpStatus.OK);
    }
}
