package com.apirest.sql.controller;

import com.apirest.sql.entity.Person;
import com.apirest.sql.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestConrtoller para saber que es un controlador rest
@RestController
//@RequestMapping("/api/v1/persons")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class PersonRestController {
    //Se inyecta el servicio
    @Autowired
    private PersonService personService;


    /* Metodo para todo el listddo de personas al ingresar a /persons *
     */
    @GetMapping("/persons")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/persons/{personId}")
    public Person getPerson(@PathVariable int personId) {
        Person person = personService.findById(personId);

        if(person == null) {
            throw new RuntimeException("Persona no encontrada - " + personId);
        }
        return person;
    }

    @PostMapping("/persons")
    //@ModelAttribute Person person,  @RequestParam("imagen") MultipartFile imagen
    public Person addPerson(@RequestBody Person person) {
        person.setId(0);
        personService.save(person);
        return person;
    }

    @PutMapping("/persons")
    public Person updatePerson(@RequestBody Person person) {
        personService.update(person);
        return person;
    }

    @DeleteMapping("/persons/{id}")
    public String deletePerson(@PathVariable int id) {
        Person person = personService.findById(id);

        if(person == null){
            throw new RuntimeException("Persona no encontrada - " + id);
        }

        personService.deleteById(id);
        return "Persona eliminada con id: " + id;
    }
}
