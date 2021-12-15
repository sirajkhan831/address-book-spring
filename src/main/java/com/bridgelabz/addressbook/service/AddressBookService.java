package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.entity.AddressBookEntity;
import com.bridgelabz.addressbook.exceptionhandler.ResourceException;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Purpose: AddressBook Service for holding the business logic.
 *
 * @author Siraj
 * @version 1.0
 * @since 11-12-2021
 **/
@Service
public class AddressBookService {

    private static final String ENT_ADDED_SUCCESSFULLY = "Entry Added Successfully";
    private static final String ENT_DELETED_SUCCESSFULLY = "Entry Deleted Successfully";
    private static final String ENT_UPDATED_SUCCESSFULLY = "Entry Updated Successfully";

    @Autowired
    AddressBookRepository bookRepository;

    @Autowired
    ModelMapper mapper;

    /**
     * Purpose : Fetches all the entry in the repository.
     *
     * @return : Returns a list of entries.
     */
    public List<AddressBookDto> getEntries() {
        List<AddressBookDto> entries = new ArrayList<>();
        for (AddressBookEntity entity : bookRepository.findAll()) {
            AddressBookDto dto = mapper.map(entity, AddressBookDto.class);
            entries.add(dto);
        }
        return entries;
    }

    /**
     * Purpose : Deletes entry from the repository.
     *
     * @return : Returns a String is the object is deleted successfully.
     */
    public String deleteEntry(int id) {
        if (bookRepository.findById(id).equals(Optional.empty())) {
            throw new ResourceException();
        }
        bookRepository.deleteById(id);
        return ENT_DELETED_SUCCESSFULLY;
    }

    /**
     * Purpose : Adds new entry in the repository.
     *
     * @return : Returns a String if the object is added successfully.
     */
    public String addEntry(AddressBookDto entryDto) {
        entryDto.setId(idGenerator());
        AddressBookEntity entryEntity = mapper.map(entryDto, AddressBookEntity.class);
        bookRepository.save(entryEntity);
        return ENT_ADDED_SUCCESSFULLY;
    }

    /**
     * Purpose : Updates new entry in the repository.
     *
     * @return : Returns a String if the object is updated successfully.
     */
    public String updateEmployee(AddressBookDto addressEntry, int id) {
        if (bookRepository.findById(id).equals(Optional.empty())) {
            throw new ResourceException();
        }
        AddressBookEntity addressEntity = mapper.map(addressEntry, AddressBookEntity.class);
        addressEntity.setId(id);
        bookRepository.save(addressEntity);
        return ENT_UPDATED_SUCCESSFULLY;
    }

    private int idGenerator() {
        int id = 0;
        for (AddressBookEntity entity : bookRepository.findAll()) {
            id++;
            if (id != entity.getId()) {
                return id;
            }
        }
        return getEntries().size() + 1;
    }
}