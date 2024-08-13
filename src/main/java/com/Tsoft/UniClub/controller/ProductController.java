package com.Tsoft.UniClub.controller;

import com.Tsoft.UniClub.utils.JwtUtilHeplers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {



    @PostMapping
    public ResponseEntity<?> addProduct(){

        return new ResponseEntity<>("hello add product", HttpStatus.OK);
    }
}
