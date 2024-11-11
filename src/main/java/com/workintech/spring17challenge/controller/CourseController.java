package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.entity.Course;
import com.workintech.spring17challenge.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private List<Course> courses = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        courses.add(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name) {
        return courses.stream()
                .filter(course -> course.getName().equals(name))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ApiException("Course not found", HttpStatus.NOT_FOUND));  // NOT_FOUND yerine BAD_REQUEST olmamalı
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course course) {
        Course existingCourse = courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApiException("Course not found", HttpStatus.NOT_FOUND));

        existingCourse.setName(course.getName());
        existingCourse.setCredit(course.getCredit());
        existingCourse.setGrade(course.getGrade());

        return ResponseEntity.ok(existingCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courses.removeIf(course -> course.getId().equals(id));
        return ResponseEntity.ok().build();
    }
}
