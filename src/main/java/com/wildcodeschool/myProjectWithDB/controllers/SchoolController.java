package com.wildcodeschool.myProjectWithDB.controllers;

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
}
