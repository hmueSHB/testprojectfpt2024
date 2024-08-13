package com.sawhtoo.testprojectfpt2024.controller;

import com.sawhtoo.testprojectfpt2024.model.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public PersonModel getUser(@PathVariable int id) {
        PersonModel person = new PersonModel();
        person.setName("Mason");
        person.setAge(id);
        return person;
    }
}
