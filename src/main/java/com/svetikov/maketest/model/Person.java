package com.svetikov.maketest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "person_1")
public class Person implements Supplier<Person> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPerson;
    private String namePerson;
    Status statusPerson;

    @Override
    public Person get() {
        return null;
    }
}
