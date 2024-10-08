package com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.response;

import com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.enums.ErrorType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private ErrorType type;
    private String message;
    private List<String> details;
    private String timestamp;
}
