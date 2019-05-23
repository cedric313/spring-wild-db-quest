package com.wildcodeschool.myProjectWithDB.controllers;

import com.wildcodeschool.myProjectWithDB.entities.Wizard;
import com.wildcodeschool.myProjectWithDB.repositories.WizardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wildcodeschool.myProjectWithDB.entities.School;
import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepositories;

import java.sql.Date;
import java.util.List;

@Controller
@ResponseBody
public class SchoolController {

    @GetMapping("/api/schools")
    public List<School> getSchool(@RequestParam(defaultValue = "%") String family) {
        return SchoolRepositories.selectByName(family);
    }

    @PostMapping("/api/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public School store(
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam String country

    ) {
        int idGeneratedByInsertion = SchoolRepositories.insert(
                name,
                capacity,
                country

        );
        return SchoolRepositories.selectById(idGeneratedByInsertion);
    }

    @PutMapping("/api/schools/{id}")
    public School update(
            @PathVariable int id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String country

    ) {
        School school = SchoolRepositories.selectById(id);
        SchoolRepositories.update(
                id,
                name != null ? name : school.getName(),
                capacity != null ? capacity : school.getCapacity(),
                country != null ? country : school.getCountry()

        );
        return SchoolRepositories.selectById(id);
    }
}
