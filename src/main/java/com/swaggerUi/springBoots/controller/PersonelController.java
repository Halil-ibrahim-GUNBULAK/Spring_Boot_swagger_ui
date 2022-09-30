package com.swaggerUi.springBoots.controller;

import com.swaggerUi.springBoots.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/personal")
@Api(value = "User api documentation")
public class PersonelController {
     private final List<Person> personalList=new ArrayList<>();


    @PostConstruct
    public void init()
    {
        personalList.add(new Person(1,"halilG", "halil",
                "günbulak", "ibrahim.gunbulak@gmail.com", new Date(),true));
    }

    @PostMapping
    @ApiOperation(value = "New person adding method")
    public ResponseEntity<Person> save(@RequestBody Person user) {
        personalList.add(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @ApiOperation(value = "person list method")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(personalList);
    }

    @PutMapping("{personalId}")
    @ApiOperation(value = "Update person method")
    public ResponseEntity<Person> updateEmployee(@PathVariable int personalId,@RequestBody Person personDetail) {
        Person updateEmployee;
        try{
           updateEmployee = personalList.get(personalId);
        }catch (Exception e){
            e.printStackTrace();
            return (ResponseEntity<Person>) ResponseEntity.notFound();
        }

        updateEmployee.setFirstName(personDetail.getFirstName());
        updateEmployee.setLastName(personDetail.getLastName());
        updateEmployee.setUserName(personDetail.getUserName());
        updateEmployee.setEmailAddress(personDetail.getEmailAddress());
        updateEmployee.setIsUserActive(personDetail.getIsUserActive());


        return ResponseEntity.ok(updateEmployee);
    }

}
