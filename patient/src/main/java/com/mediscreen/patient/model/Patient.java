package com.mediscreen.patient.model;

import com.mediscreen.patient.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "patients")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "The last name is required")
    @Column(name = "last_name", length = 100)
    private String lastName;

    @NotBlank(message = "The first name is required")
    @Column(name = "first_name", length = 100)
    private String firstName;

    @NotBlank(message = "The birthdate is required")
    private LocalDate birthdate;

    @NotBlank(message = "The gender is required")
    @Column(length = 1)
    @Enumerated(EnumType.STRING)
    private Gender sex;

    @Column(length = 200)
    private String address;

    @Column(length = 50)
    private String phone;
}
