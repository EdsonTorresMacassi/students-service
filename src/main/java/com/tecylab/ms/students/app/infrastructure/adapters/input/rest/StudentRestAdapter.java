package com.tecylab.ms.students.app.infrastructure.adapters.input.rest;

import com.tecylab.ms.students.app.application.ports.input.StudentInputPort;
import com.tecylab.ms.students.app.infrastructure.adapters.input.rest.mapper.StudentRestMapper;
import com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.request.StudentCreateRequest;
import com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.request.StudentUpdateRequest;
import com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.response.StudentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentRestAdapter {

    private final StudentInputPort inputPort;
    private final StudentRestMapper restMapper;

    @GetMapping
    public List<StudentResponse> findAll() {
        return restMapper.toStudentResponses(inputPort.findAll());
    }

    @GetMapping("/{id}")
    public StudentResponse findById(@PathVariable Long id) {
        return restMapper.toStudentResponse(inputPort.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> save(@Valid @RequestBody StudentCreateRequest request) {
        StudentResponse response = restMapper.toStudentResponse(inputPort.save(restMapper.toStudent(request)));
        //return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return ResponseEntity.created(URI.create("/students/".concat(response.getId().toString()))).body(response);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentCreateRequest request) {
        return restMapper.toStudentResponse(inputPort.update(id, restMapper.toStudent(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        inputPort.deleteById(id);
    }

}
