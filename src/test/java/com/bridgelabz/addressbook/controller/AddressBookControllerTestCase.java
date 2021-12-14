package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.controller.AddressBookController;
import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.service.AddressBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressBookControllerTestCase {

    @InjectMocks
    private AddressBookController controller;

    @Mock
    private AddressBookService service;

    private AddressBookDto addressDto;
    private AddressBookDto addressDto2;

    @BeforeEach
    void setUp() {
        addressDto = new AddressBookDto();
        addressDto.setName("Siraj Khan");
        addressDto.setAddress("100East");
        addressDto.setCity("Raipur");
        addressDto.setState("Chhattisgarh");
        addressDto.setPhoneNumber("8888888888");
        addressDto.setZip(492001);
        addressDto2 = new AddressBookDto();
        addressDto2.setName("Rahul Singh");
        addressDto2.setAddress("200West");
        addressDto2.setCity("Delhi");
        addressDto2.setState("Delhi");
        addressDto2.setPhoneNumber("7888888888");
        addressDto2.setZip(292001);
    }

    @Test
    void givenTwoEmployeeDto_whenGetCalled_shouldReturnListOfEmployee() {
        List<AddressBookDto> addressBookDtoList = new ArrayList<>();
        service.addEntry(addressDto);
        service.addEntry(addressDto);
        addressBookDtoList.add(addressDto);
        addressBookDtoList.add(addressDto2);
        when(service.getEntries()).thenReturn(addressBookDtoList);
        List<AddressBookDto> actualResponse = controller.getEntries().getBody();
        for (int i = 0; i < Objects.requireNonNull(actualResponse).size(); i++) {
            Assertions.assertEquals(addressBookDtoList.get(i).getName(), actualResponse.get(i).getName());
            Assertions.assertEquals(addressBookDtoList.get(i).getCity(), actualResponse.get(i).getCity());
            Assertions.assertEquals(addressBookDtoList.get(i).getState(), actualResponse.get(i).getState());
        }
    }

    @Test
    void givenEmployeeDto_whenAdded_shouldReturnResponseEntity() {
        String successMessage = "Employee Added Successfully";
        when(service.addEntry(addressDto)).thenReturn(successMessage);
        ResponseEntity<String> responseEntity = controller.addEntry(addressDto);
        Assertions.assertEquals(successMessage, responseEntity.getBody());
        Assertions.assertEquals(successMessage, responseEntity.getBody());
    }

    @Test
    void givenEmployeeDto_whenUpdatedEmployee_shouldReturnResponseEntity() {
        String successMessage = "Employee Updated Successfully";
        int id = 1;
        when(service.updateEmployee(addressDto, id)).thenReturn(successMessage);
        ResponseEntity<String> responseEntity = controller.updateEntry(id, addressDto);
        Assertions.assertEquals(successMessage, responseEntity.getBody());
    }

    @Test
    void givenEmployeeId_whenDeleted_shouldReturnResponseEntity() {
        String successMessage = "Employee Deleted Successfully";
        int id = 1;
        when(service.deleteEntry(id)).thenReturn(successMessage);
        ResponseEntity<String> responseEntity = controller.deleteEntry(id);
        Assertions.assertEquals(successMessage, responseEntity.getBody());
    }
}
