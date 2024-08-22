package com.Tsoft.UniClub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data// tự động tạo Getter Setter
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "information")
    private String information;

    @Column(name = "price")
    private double price;

    @Column(name = "create_date")
    private LocalDateTime createDate;


    @ManyToOne
    @JoinColumn(name = "id_brand")
    private BrandEntity brandEntity;


    @OneToMany(mappedBy = "productEntity")
    private List<VariantEntity> variantEntityList;

}
