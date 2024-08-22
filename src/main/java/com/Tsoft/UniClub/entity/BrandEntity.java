package com.Tsoft.UniClub.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data// tự động tạo Getter Setter
@Entity(name = "brand")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brandEntity")
    private List<ProductEntity> productEntityList;
}
