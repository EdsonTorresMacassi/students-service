package com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {

    @NotBlank(message = "Field firstname cannot be null or blank")
    private String firstname;

    @NotBlank(message = "Field lastname cannot be null or blank")
    private String lastname;

    @NotNull(message = "Field age cannot be null")
    @Min(value = 1, message = "Field age must be greater than zero")
    private Integer age;

    @NotNull(message = "Field email cannot be null or blank")
    @Email(message = "Field email invalid format")
    private String email;

    @NotBlank(message = "Field address cannot be null or blank")
    private String address;

}
