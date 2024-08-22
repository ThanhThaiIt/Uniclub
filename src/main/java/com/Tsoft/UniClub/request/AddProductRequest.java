package com.Tsoft.UniClub.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


public record AddProductRequest(
        String name,
        String desc,
        String information,
        double price,
        int idBrand,
        int idColor,
        int idSize,
        MultipartFile files,
        int quantity,
        double priceSize
) {
}
