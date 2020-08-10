package com.svetikov.maketest;

import com.svetikov.maketest.model.Person;
import com.svetikov.maketest.model.Status;
import com.svetikov.maketest.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AnyController {

    private final PersonService personService;

    public AnyController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public ResponseEntity<Person> firstTest(){
        return ResponseEntity.ok(new Person(1,"Veine", Status.One));
    }

    @PostMapping("/person")
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        log.info("this new person "+person.toString());
        personService.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> findID(@PathVariable("id") int id){

        return ResponseEntity.ok(personService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> allPerson(){

        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Person> findByName(@PathVariable("name") String name){
        log.info("find by name "+name);
        return ResponseEntity.ok(personService.findByName(name));
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Person> editPerson(@PathVariable(name = "id") int id,@RequestBody Person person){
       Person personOld= personService.findById(id);
       personOld.setNamePerson(person.getNamePerson());
       personOld.setStatusPerson(person.getStatusPerson());
       personService.save(personOld);
        return ResponseEntity.ok(personOld);
    }
}
