package com.Tsoft.UniClub.controller;

import com.Tsoft.UniClub.model.ResponseMessage;
import com.Tsoft.UniClub.request.AddProductRequest;
import com.Tsoft.UniClub.response.BaseResponse;
import com.Tsoft.UniClub.service.FilesStorageService;
import com.Tsoft.UniClub.service.ProductService;
import com.Tsoft.UniClub.utils.JwtUtilHeplers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping("/upload")
    public ResponseEntity<?> addProduct(AddProductRequest addProductRequest){

        productService.addProduct(addProductRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("success !!");
        baseResponse.setStatusCode(200);
       return new ResponseEntity<>(baseResponse, HttpStatus.OK);


    }
}
