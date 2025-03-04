package com.example.MongodbPractice.Controller;

import com.example.MongodbPractice.Dto.InstructorDto;
import com.example.MongodbPractice.Dto.StudentDto;
import com.example.MongodbPractice.Services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    // Assign instructor to a course
    @PostMapping("/{instructorId}/courses/{courseId}")
    public ResponseEntity<InstructorDto> assignCourse(
            @PathVariable String instructorId,
            @PathVariable String courseId) {
        InstructorDto instructor = instructorService.assignCourse(instructorId, courseId);
        return ResponseEntity.ok(instructor);
    }

    // Remove instructor from a course
    @DeleteMapping("/{instructorId}/course")
    public ResponseEntity<Void> removeFromCourse(@PathVariable String instructorId) {
        instructorService.removeFromCourse(instructorId);
        return ResponseEntity.noContent().build();
    }

    //Add an Instructor
    @PostMapping("/addInstructor")
    public String addInstructor(@RequestBody InstructorDto instructorDto){
        return instructorService.addInstructor(instructorDto);
    }

    //Get all Instructors
    @GetMapping("/getInstructor")
    public ResponseEntity<List<InstructorDto>> getAllInstructors() {
        List<InstructorDto> instructorList = instructorService.getAllInstructors();
        return new ResponseEntity<List<InstructorDto>>(instructorList, HttpStatus.OK);
    }
    //Get Instructor by Id
    @GetMapping("/getInstructorById/{Id}")
    public String getInstructor(@PathVariable String Id){
        return instructorService.getInstructor(Id);
    }
    //Delete Instructor by Id
    @DeleteMapping("/deleteInstructorById/{Id}")
    public boolean deleteInstructor(@PathVariable String Id){
        return instructorService.deleteInstructor(Id);
    }
}
