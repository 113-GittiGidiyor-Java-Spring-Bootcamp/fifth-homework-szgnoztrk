package dev.patika.app.api.controller;

import dev.patika.app.bussiness.abstracts.InstructorService;
import dev.patika.app.entity.dto.InstructorDto;
import dev.patika.app.entity.enums.PercentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/instructors")
public class InstructorsController {
    private final InstructorService instructorService;
    @GetMapping
    public ResponseEntity getAllInstructors(){
        return new ResponseEntity(this.instructorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getInstructorById(@PathVariable Long id){
        return new ResponseEntity(this.instructorService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveInstructor(@RequestBody InstructorDto instructorDto){
        return new ResponseEntity(this.instructorService.save(instructorDto), HttpStatus.OK);
    }

    @PutMapping("/updateSalary")
    public ResponseEntity updateSalary(@RequestParam double percent,
                                       @RequestParam PercentType percentType,
                                       @RequestParam long instructorId){
        return new ResponseEntity(this.instructorService.updateSalary(instructorId, percent, percentType).get(), HttpStatus.OK);
    }
}
