package com.Tsoft.UniClub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data// tự động tạo Getter Setter
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;
}
