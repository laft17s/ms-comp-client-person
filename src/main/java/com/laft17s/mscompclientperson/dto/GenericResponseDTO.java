package com.laft17s.mscompclientperson.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.print.attribute.IntegerSyntax;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GenericResponseDTO {

    private Integer code;
    private Status status;
    private List<Object> payload;
    private String message;

    public enum Status {
        OK, ERROR, WARNING
    }
}
