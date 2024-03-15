package com.example.demo.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
    public String storeFile(MultipartFile file);
    public Stream<Path> loadAll();
    public byte[] readFileContent(String filename);

    public String uploadFile(String filename, MultipartFile file);
    public void deleleAllFiles(String filename);

}
