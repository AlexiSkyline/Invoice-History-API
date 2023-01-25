package com.alexiskyline.inventory.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadFileService {
    Resource load(String namePhoto) throws MalformedURLException;
    String copy(MultipartFile file) throws IOException;
    boolean delete(String namePhoto);
    Path getPath(String namePhoto);
}