package com.example.HotelBooking.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;


    private String password;
    private String firstName;
    private String lastName;


    private String phoneNumber;

    private UserRole role;

    private Boolean isActive;
    private final LocalDateTime createdAt = LocalDateTime.now();





}
