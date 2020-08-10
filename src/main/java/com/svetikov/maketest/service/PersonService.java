package com.svetikov.maketest.service;

import com.svetikov.maketest.model.Person;
import com.svetikov.maketest.model.Status;
import com.svetikov.maketest.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonService implements CommonService<Person> {

    private final PersonRepository personRepository;
    private Person person;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(Person person) {
        log.info("save " + person.toString());
        personRepository.save(person);
    }

    @Override
    public Person findById(int id) {
        return personRepository.findById(id).orElseThrow(NullPointerException::new);
    }




    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByName(String name) {
        Optional<Person> personOption = null;

        try {
            personOption = Optional.of(personRepository.findByNamePerson(name));
        } catch (NullPointerException e) {
            personOption = Optional.empty();
        }

        personOption.ifPresentOrElse(

                person1 -> {
                    person = person1;
                    log.info(person1.toString());
                },
                () -> {
                    person = new Person(1, name, Status.None);
                    log.info("create new person and save to database " + name);
                    save(person);
                }
        );


        log.info(person.toString());
        return person;
    }
}
