package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.entity.AddressBookEntity;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {

    private static final String ENT_ADDED_SUCCESSFULLY = "Entry Added Successfully";
    private static final String ENT_DELETED_SUCCESSFULLY = "Entry Deleted Successfully";
    private static final String ENT_UPDATED_SUCCESSFULLY = "Entry Updated Successfully";

    @Autowired
    AddressBookRepository bookRepository;

    @Autowired
    ModelMapper mapper;

    public String addEmp(AddressBookDto entryDto) {
        AddressBookEntity entryEntity = mapper.map(entryDto, AddressBookEntity.class);
        bookRepository.save(entryEntity);
        return ENT_ADDED_SUCCESSFULLY;
    }

    public List<AddressBookDto> getEntries() {
        List<AddressBookDto> entries = new ArrayList<>();
        for (AddressBookEntity entity : bookRepository.findAll()) {
            AddressBookDto dto = mapper.map(entity, AddressBookDto.class);
            dto.setId(entity.getId());
            entries.add(dto);
        }
        return entries;
    }

    public String deleteEntry(int id) {
        bookRepository.deleteById(id);
        return ENT_DELETED_SUCCESSFULLY;
    }

    public String updateEmployee(AddressBookDto addressEntry) {
        AddressBookEntity addressEntity = mapper.map(addressEntry, AddressBookEntity.class);
        addressEntity.setId(dtoToEntity(addressEntry).getId());
        bookRepository.save(addressEntity);
        return ENT_UPDATED_SUCCESSFULLY;
    }

    public AddressBookEntity dtoToEntity(AddressBookDto dto) {
        for (AddressBookEntity emp : bookRepository.findAll()) {
            if (emp.getName().equals(dto.getName())) {
                return emp;
            }
        }
        return null;
    }
}
