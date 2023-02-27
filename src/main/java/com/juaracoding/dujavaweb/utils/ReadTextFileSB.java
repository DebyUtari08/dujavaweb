package com.juaracoding.dujavaweb.utils;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 27/02/2023 22:35
@Last Modified 27/02/2023 22:35
Version 1.0
*/

import com.juaracoding.dujavaweb.handler.ResourceNotFoundException;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;


public class ReadTextFileSB {
    private FileInputStream fInput;
    private BufferedInputStream bInput;
    private String[] exceptionString = new String[2];
    private String contentFile;
    private byte[] contentOfFile;
    private String filePathz ;

    public ReadTextFileSB(String pathFiles) throws ResourceNotFoundException {
        setContentFile(pathFiles);
    }

    public void setContentFile(String pathFile) throws ResourceNotFoundException {

        try {
            File filez = ResourceUtils.getFile("classpath:"+pathFile);
            fInput = new FileInputStream(filez);
            bInput = new BufferedInputStream(fInput);
            byte[] bdata = FileCopyUtils.copyToByteArray(bInput);
            String data = new String(bdata, StandardCharsets.UTF_8);
            this.contentOfFile = bdata;
            this.contentFile = new String(contentOfFile, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        finally {
            try {
                fInput.close();
                bInput.close();
            } catch (Exception e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        }
    }

    public String getContentFile()
    {
        return contentFile;
    }

    public byte[] getByteOfFile()
    {
        return contentOfFile;
    }

    public static void main(String[] args) throws ResourceNotFoundException {
        ReadTextFileSB rtfSB = new ReadTextFileSB("\\data\\MailHTMLExample.txt");
        System.out.println("STRING => "+rtfSB.getContentFile());
        System.out.println("BYTE FILE => "+rtfSB.getByteOfFile());
    }
}
