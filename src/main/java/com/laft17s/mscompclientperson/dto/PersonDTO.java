package com.laft17s.mscompclientperson.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String dni;
    private String address;
    private String phoneNumber;
}
