package com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private String address;
    private String timestamp;
}
