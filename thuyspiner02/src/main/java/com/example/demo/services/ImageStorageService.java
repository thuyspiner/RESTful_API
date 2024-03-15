package com.example.demo.services;

import jakarta.annotation.Resource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageService implements IStorageService{
    private final Path storageFolder = Paths.get("uploads");
    public ImageStorageService() {
        try {
            Files.createDirectories(storageFolder);
        } catch (IOException exceptionO) {
            throw new RuntimeException("Cannot initialize storage", exceptionO);
        }

    }
    public boolean isImageFile(MultipartFile file) {
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            return Arrays.asList( new String[] {"png", "jpg", "jpeg", "bmp"})
                    .contains(fileExtension.trim().toLowerCase());
    }
    @Override
    public String storeFile(MultipartFile file) {
        try {
            System.out.println("Huhu");
            if (file.isEmpty()) {
                throw  new RuntimeException("failed to store empty file");
            }
            if (!isImageFile(file)) {
                throw  new RuntimeException("you can only upload image file");
            }
            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if (fileSizeInMegabytes>5.0f) {
                throw new RuntimeException("File must be <= 5Mb");
            }
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName+"."+fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(Paths.get(generatedFileName))
                    .normalize().toAbsolutePath();
            if(!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directiory");
            }
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        } catch (IOException exception0) {
            throw new RuntimeException("Failed to store file", exception0);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.list(this.storageFolder);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load all files", e);
        }
    }

    @Override
    public byte[] readFileContent(String filename) {
        try {
            Path filePath = this.storageFolder.resolve(filename).normalize().toAbsolutePath();

            // Đọc dữ liệu từ tệp tin vào một danh sách byte
            List<Byte> byteList = new ArrayList<>();
            byte[] fileContent = Files.readAllBytes(filePath);

            for (byte b : fileContent) {
                byteList.add(b);
            }

            // Chuyển danh sách byte thành mảng byte
            byte[] result = new byte[byteList.size()];
            for (int i = 0; i < byteList.size(); i++) {
                result[i] = byteList.get(i);
            }

            return result;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content" , e);
        }
    }

    @Override
    public String uploadFile(String filename, MultipartFile file) {
        try {
            Path filePath = this.storageFolder.resolve(filename).normalize().toAbsolutePath();
            if(!Files.exists(filePath)) {
                throw new RuntimeException("File not found");
            }
            if(!isImageFile(file)) {
                throw new RuntimeException("You can only upload image file");
            }
            //kiểm tra kích cỡ
            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if(fileSizeInMegabytes > 5.0f) {
                throw new RuntimeException("File must be <=5mb");
            }
            //Sao chép file mới vào thư mục lưu trữ
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String updatedFileName = UUID.randomUUID().toString().replace("-", "");
            updatedFileName = updatedFileName + "." + fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(Paths.get(updatedFileName)).normalize().toAbsolutePath();
            if(!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException("Cannot stored file");
            }
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

            }
            Files.delete(filePath);

            return updatedFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to updated file", e);
        }
    }


    @Override
    public void deleleAllFiles(String filename) {
        try {
            Path filePath = this.storageFolder.resolve(filename).normalize().toAbsolutePath();
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException("File to delete file", e);
        }
    }
}
