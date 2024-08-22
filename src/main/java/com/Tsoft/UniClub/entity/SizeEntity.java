package com.Tsoft.UniClub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data// tự động tạo Getter Setter
@Entity(name = "size")
public class SizeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name")
    private String name;



    @OneToMany(mappedBy = "sizeEntity")
    private List<VariantEntity> variantEntityList;
}
