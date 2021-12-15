package com.bridgelabz.addressbook.integration;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.service.AddressBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AddressBookDto.class)
public class AddressBookIntegrationTestCase {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressBookService service;

    AddressBookDto addressDto;
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonRequest;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        addressDto = new AddressBookDto();
        addressDto.setName("Siraj Khan");
        addressDto.setAddress("100East");
        addressDto.setCity("Raipur");
        addressDto.setState("Chhattisgarh");
        addressDto.setPhoneNumber("8888888888");
        addressDto.setZip(492001);
        jsonRequest = objectMapper.writeValueAsString(addressDto);
    }

    @Test
    void whenSentGetRequest_shouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/book/get-all-entries"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenSentPostRequest_ShouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/book/add-entry")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenSentDeleteRequest_ShouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/book/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenSentPutRequest_ShouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/book/update-entry/1")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenSentInvalidPostRequest_ShouldReturnStatusBadRequest() throws Exception {
        addressDto.setZip(492008);
        jsonRequest = objectMapper.writeValueAsString(addressDto);
        mvc.perform(MockMvcRequestBuilders
                        .post("/payroll/add-employee")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
