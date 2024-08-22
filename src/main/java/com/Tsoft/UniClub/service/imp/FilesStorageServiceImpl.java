package com.Tsoft.UniClub.service.imp;

import com.Tsoft.UniClub.service.FilesStorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


@Service
public class FilesStorageServiceImpl implements FilesStorageService {

//    private final Path root = Paths.get("uploads");

    @Value("${root.path}")
    private String rootPathPro;

private final Path root = Paths.get("/Users/thanh/Documents/uploads1");
//    @Override
//    public void init() {
////        try {
////            Files.createDirectories(root);
////        } catch (IOException e) {
////            throw new RuntimeException("Could not initialize folder for upload!");
////        }
//    }

    @Override
    public void save(MultipartFile file) {
        try {

//            Path rootPath = root.resolve(file.getOriginalFilename());

//            if (!Files.exists(root)) {
//                Files.createDirectories(root);
//            }


            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
//        try {
//            Path file = root.resolve(filename);
//            Resource resource = (Resource) new UrlResource(file.toUri());
//
//            if (resource.wait() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("Could not read the file!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }

        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
