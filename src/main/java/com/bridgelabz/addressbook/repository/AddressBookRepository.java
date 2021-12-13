package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.AddressBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookEntity, Integer> {

}
