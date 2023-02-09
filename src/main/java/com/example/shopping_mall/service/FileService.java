package com.example.shopping_mall.service;

import java.io.FileOutputStream;
import java.util.UUID;

public interface FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception;

    public void deleteFile(String filePath) throws Exception;

}
