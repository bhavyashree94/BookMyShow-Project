package com.example.Book_My_Show.Modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "UserDetails")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;

    private String userMobile;

    private String userEmail;

    private int age;

    private String address;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Ticket> ticketList = new ArrayList<>();

}
