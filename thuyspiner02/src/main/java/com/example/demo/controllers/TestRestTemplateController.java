package com.example.demo.controllers;

import com.example.demo.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class TestRestTemplateController {
    //employee API
    private static final String GET_ALL_EMPLOYEES_API = "http://localhost:8888/emp/employees";
    private static final String CREATE_EMPLOYEES_API = "http://localhost:8888/emp/employees";
    private static final String GET_EMPLOYEE_BY_ID = "http://localhost:8888/emp/employees/{id}";


    //FileUploadImage API
    private static final String CREATE_IMAGE_FILE = "http://localhost:8888/api/v1/FileUpLoad/createFile";
    private static final String GET_IMAGE_FILE_BYNAME = "http://localhost:8888/api/v1/FileUpLoad/files/{fileName:.+}";
    private static final String GET_IMAGE_FILE_BYPATH = "http://localhost:8888/api/v1/FileUpLoad/getAllImagePath";
    private static final String UPDATE_IMAGE_FILE = "http://localhost:8888/api/v1/FileUpLoad/files/{filename:.+}";
    private static final String DELETE_IMAGE_FILE = "http://localhost:8888/api/v1/FileUpLoad/files/{filename:.+}";


    RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        TestRestTemplateController controller = new TestRestTemplateController();
        //employee
        controller.callGetAllEmployees();
        controller.callCreateEmployees();
        controller.callGetEmployeeById();

        //fileUploadImage
        controller.callCreateImageFile();
        controller.callgetAllImageFilePath();
        controller.callGetImageFileByName();
        controller.callUpdateImage();
        controller.callDeleteImageByName();
    }

//    hàm thực thi api employee
    private void callGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                GET_ALL_EMPLOYEES_API,
                HttpMethod.GET,
                entity,
                String.class);

        System.out.println("Get All Employees Response: " + response.getBody());
    }

    private void callCreateEmployees() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"id\":\"123\",\"name\":\"Thuy Spiner\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                CREATE_EMPLOYEES_API,
                HttpMethod.POST,
                entity,
                String.class);

        System.out.println("Create Employees Response: " + response.getBody());
    }

    private void callGetEmployeeById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                GET_EMPLOYEE_BY_ID,
                HttpMethod.GET,
                entity,
                String.class,
                "123");

        System.out.println("Get Employee by ID Response: " + response.getBody());
    }

    //hàm thực thi fileupload
    private void callCreateImageFile() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource("C://Users//thuy//OneDrive//Desktop//BackGround//_p03i1__winter_wanderer_ahri_by_namakxin_dg25wqh-pre.jpg"));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                CREATE_IMAGE_FILE,
                HttpMethod.POST,
                requestEntity,
                String.class);

        System.out.println("Create Image File Response: " + response.getBody());
    }

    private void callGetImageFileByName() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                GET_IMAGE_FILE_BYNAME,
                HttpMethod.GET,
                entity,
                byte[].class,
                "ec29b93ca9444ddcb57f64202636688c.jpg");

        System.out.println("Get Image File by Name Response: " + response.getStatusCode());
    }

    private void callgetAllImageFilePath() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                GET_IMAGE_FILE_BYPATH,
                HttpMethod.GET,
                entity,
                String.class);

        System.out.println("Get Image File by Path Response: " + response.getBody());
    }

    private void callUpdateImage() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource("C://Users//thuy//OneDrive//Desktop//BackGround//_p06e4__zero_two_by_namakxin_dg6fjoo-pre.jpg"));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                UPDATE_IMAGE_FILE,
                HttpMethod.PUT,
                requestEntity,
                String.class,
                "8f08c29f3c1c47e581c6cb748401889f.jpg");

        System.out.println("Update Image Response: " + response.getBody());
    }

    private void callDeleteImageByName() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                DELETE_IMAGE_FILE,
                HttpMethod.DELETE,
                entity,
                String.class,
                "ec29b93ca9444ddcb57f64202636688c.jpg");

        System.out.println("Delete Image by Name Response: " + response.getBody());
    }

}
