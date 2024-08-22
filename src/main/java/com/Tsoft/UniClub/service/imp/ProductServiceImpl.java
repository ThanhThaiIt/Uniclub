package com.Tsoft.UniClub.service.imp;

import com.Tsoft.UniClub.entity.*;
import com.Tsoft.UniClub.repository.ProductRepository;
import com.Tsoft.UniClub.repository.VariantRepository;
import com.Tsoft.UniClub.request.AddProductRequest;
import com.Tsoft.UniClub.service.FilesStorageService;
import com.Tsoft.UniClub.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private FilesStorageService filesStorageService;

    @Transactional
    @Override
    public void addProduct(AddProductRequest addProductRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(addProductRequest.name());
        productEntity.setPrice(addProductRequest.price());
        productEntity.setDescription(addProductRequest.desc());
        productEntity.setInformation(addProductRequest.information());

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(addProductRequest.idBrand());

        productEntity.setBrandEntity(brandEntity);

        ProductEntity productInserted =  productRepository.save(productEntity);
        VariantEntity variantEntity = new VariantEntity();
        variantEntity.setProductEntity(productInserted);

        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setId(addProductRequest.idColor());
        variantEntity.setColorEntity(colorEntity);

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(addProductRequest.idSize());
        variantEntity.setSizeEntity(sizeEntity);
        variantEntity.setPrice(addProductRequest.price());
        variantEntity.setQuantity(addProductRequest.quantity());
        variantEntity.setImages(addProductRequest.files().getOriginalFilename());


        variantRepository.save(variantEntity);

        filesStorageService.save(addProductRequest.files());
    }
}
