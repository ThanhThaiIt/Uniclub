package com.Tsoft.UniClub.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data// tự động tạo Getter Setter
@Entity(name = "variant")
public class VariantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sku;

    @Column(name = "images")
    private String images;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity productEntity;


    @ManyToOne
    @JoinColumn(name = "id_color")
    private ColorEntity  colorEntity;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private SizeEntity sizeEntity;
}
