package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.entity.AddressBookEntity;
import com.bridgelabz.addressbook.exceptionhandler.ResourceException;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceTestCase {

    @InjectMocks
    private AddressBookService service;

    @Mock
    private AddressBookRepository repository;

    @Mock
    private ModelMapper modelMapper;

    AddressBookDto addressDto;
    AddressBookDto addressDto2;

    AddressBookEntity addressBookEntity;
    AddressBookEntity addressBookEntity2;

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

        addressBookEntity = new AddressBookEntity();
        addressBookEntity.setName("Siraj Khan");
        addressBookEntity.setAddress("100East");
        addressBookEntity.setCity("Raipur");
        addressBookEntity.setState("Chhattisgarh");
        addressBookEntity.setPhoneNumber("8888888888");
        addressBookEntity.setZip(492001);
        addressBookEntity2 = new AddressBookEntity();
        addressBookEntity2.setName("Rahul Singh");
        addressBookEntity2.setAddress("200West");
        addressBookEntity2.setCity("Delhi");
        addressBookEntity2.setState("Delhi");
        addressBookEntity2.setPhoneNumber("7888888888");
        addressBookEntity2.setZip(292001);
    }

    @Test
    void givenAListOfEmployees_whenGetEmployeeCalled_shouldReturnListOfEmployee() {
        List<AddressBookDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(addressDto);
        employeeDtoList.add(addressDto2);

        List<AddressBookEntity> entityList = new ArrayList<>();
        entityList.add(addressBookEntity);
        entityList.add(addressBookEntity2);

        lenient().when(repository.findAll()).thenReturn(entityList);
        when(modelMapper.map(entityList.get(0), AddressBookDto.class)).thenReturn(addressDto);
        when(modelMapper.map(entityList.get(1), AddressBookDto.class)).thenReturn(addressDto2);
        List<AddressBookDto> actualListOfEmployee = service.getEntries();
        Assertions.assertEquals(2, actualListOfEmployee.size());
        Assertions.assertEquals(employeeDtoList, actualListOfEmployee);
    }

    @Test
    void givenEmployeeDto_whenCalledAddEmployee_shouldReturnSuccessMessage() {
        String successMessage = "Entry Added Successfully";
        when(modelMapper.map(addressDto, AddressBookEntity.class)).thenReturn(addressBookEntity);
        String actualMessage = service.addEntry(addressDto);
        Assertions.assertEquals(successMessage, actualMessage);
        verify(repository, times(1)).save(addressBookEntity);
    }

    @Test
    void givenEmployeeIdPayrollDto_whenCalledDeleteEmployee_shouldReturnSuccessMessage() {
        String successMessage = "Entry Deleted Successfully";
        lenient().when(repository.findById(1)).thenReturn(Optional.of(addressBookEntity));
        String actualMessage = service.deleteEntry(1);
        Assertions.assertEquals(successMessage, actualMessage);
    }

    @Test
    void givenAEmployeeDetails_whenUpdateEmployeeIsCalled_shouldThrowExceptionMessage() {
        int employeeId = 4;
        when(repository.findById(employeeId)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceException.class, () -> service.updateEmployee(addressDto, employeeId));
    }
}
