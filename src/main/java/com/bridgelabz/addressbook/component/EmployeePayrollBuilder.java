package com.bridgelabz.addressbook.component;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.entity.AddressBookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeePayrollBuilder {

    @Autowired
    ModelMapper mapper;

    public AddressBookEntity builder(AddressBookDto dto, AddressBookEntity entity) {
        return mapper.map(dto, AddressBookEntity.class);
    }
}
