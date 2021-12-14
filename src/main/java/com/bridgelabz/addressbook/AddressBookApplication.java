package com.bridgelabz.addressbook;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Purpose: AddressBook system application created to manage address entries.
 *
 * @author Siraj
 * @version 1.0
 * @since 10-12-2021
 **/
@SpringBootApplication
public class AddressBookApplication {

    /**
     * Purpose : Creating instance of Model mapper to map objects and entities.
     *
     * @return : Returns a new model mapper
     */
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }

}
