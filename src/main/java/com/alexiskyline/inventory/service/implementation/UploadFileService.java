package com.alexiskyline.inventory.service.implementation;

import com.alexiskyline.inventory.service.IUploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UploadFileService implements IUploadFileService {
    private final static String DIRECTORY_UPLOAD = "uploads";
    private final static String FILE_PATH = "src/main/resources/static/images";

    @Override
    public Resource load(String namePhoto) throws MalformedURLException {
        Path filePath = this.getPath(namePhoto);
        log.info(filePath.toString());
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            filePath = Paths.get(FILE_PATH)
                    .resolve("no-user.png").toAbsolutePath();
            resource = new UrlResource(filePath.toUri());
            log.error("Error. Unable to load image");
        }
        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String fileName = this.generateFileName(file);
        Path filePath = this.getPath(fileName);
        log.info(filePath.toString());
        Files.copy(file.getInputStream(), filePath);
        return fileName;
    }

    @Override
    public boolean delete(String namePhoto) {
        if (namePhoto != null && namePhoto.length() > 0) {
            Path routePreviousPhoto = Paths.get("uploads").resolve(namePhoto).toAbsolutePath();
            File filePreviousPhoto = routePreviousPhoto.toFile();

            if (filePreviousPhoto.exists() && filePreviousPhoto.canRead()) {
                filePreviousPhoto.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String namePhoto) {
        return Paths.get(DIRECTORY_UPLOAD).resolve(namePhoto).toAbsolutePath();
    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID()
                .toString() + "_" + Objects.requireNonNull(file.getOriginalFilename()).
                replace(" ", "");
    }
}