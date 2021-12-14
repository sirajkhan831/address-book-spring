package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.AddressBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *  Purpose: AddressBook Repository for handling database repository.
 *  @version 1.0
 *  @since 11-12-2021
 **/
@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookEntity, Integer> {

}
