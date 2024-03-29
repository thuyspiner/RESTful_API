package com.example.demo.controllers;

import com.example.demo.models.ResponseObject;
import com.example.demo.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/v1/FileUpLoad")
public class FileUploadController {
    @Autowired
    private IStorageService storageService;
    private final Path storageFolder = Paths.get("uploads");
    @PostMapping("createFile")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "up load file successfully", generatedFileName)
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("ok", exception.getMessage(), "")
            );
        }
    }
    //get image's url
    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
    //load all uploaded files
    @GetMapping("getAllImagePath")
    public ResponseEntity<ResponseObject> getUploadedFile() {
        try {
               //convert filename to url (gửi request "readdetailfile)
               List<String> urls = storageService.loadAll().map(path ->  {
                   String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,"readDetailFile",
                           path.getFileName().toString()).build().toUri().toString();
                   return urlPath;
               })
               .collect(Collectors.toList());
               return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("failed", "List files failed", new String[] {}));
        }

    }
    @PutMapping("/files/{filename:.+}")
    public ResponseEntity<ResponseObject> updateFile(@PathVariable String filename,
                                                     @RequestParam("file") MultipartFile file) {
        try {
            String updateFileName = storageService.uploadFile(filename, file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "File updated successfully", updateFileName)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("Failed", "Failed to update file", "")
            );
        }
    }
    @DeleteMapping("/files/{filename:.+}")
    public ResponseEntity<ResponseObject> deleteFile(@PathVariable String filename) {
        try {
            // Kiểm tra sự tồn tại của file trước khi xóa
            Path filePath = this.storageFolder.resolve(filename).normalize().toAbsolutePath();
            if (!Files.exists(filePath)) {
                return ResponseEntity.ok().body(
                        new ResponseObject("Failed", "File không tồn tại", "")
                );
            }
            storageService.deleleAllFiles(filename);
            return ResponseEntity.ok().body(
                    new ResponseObject("Ok", "Delete file image successfully", filename)
            );

        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("Failed", "Cannot delete file image", "")
            );
        }
    }
}
