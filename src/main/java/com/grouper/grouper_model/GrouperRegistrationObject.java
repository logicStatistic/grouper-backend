package com.grouper.grouper_model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class GrouperRegistrationObject {

    private String firstName;
    private String lastName;
    private String email;
    private Date   dob;
}
