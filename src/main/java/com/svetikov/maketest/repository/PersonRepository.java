package com.svetikov.maketest.repository;

import com.svetikov.maketest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByNamePerson(String namePerson);
}
