package com.juaracoding.dujavaweb.service;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 27/02/2023 23:27
@Last Modified 27/02/2023 23:27
Version 1.0
*/

import com.juaracoding.dujavaweb.configuration.OtherConfig;
import com.juaracoding.dujavaweb.core.BcryptImpl;
import com.juaracoding.dujavaweb.handler.ResponseHandler;
import com.juaracoding.dujavaweb.model.User;
import com.juaracoding.dujavaweb.repo.UserRepo;
import com.juaracoding.dujavaweb.utils.ConstantMessage;
import com.juaracoding.dujavaweb.utils.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class UserService {

    private UserRepo userRepo;

    private String [] strExceptionArr = new String[2];

    @Autowired
    public UserService(UserRepo userService) {
        strExceptionArr[0] = "UserService";
        this.userRepo = userService;
    }

    public Map<String,Object> checkRegis(User userz,WebRequest request) {
        int intVerification = new Random().nextInt(100000,999999);
        List<User> listUserResult = userRepo.findByEmail(userz.getEmail());
        try
        {
            if(listUserResult.size()!=0)//kondisi mengecek apakah user terdaftar
            {
                if(listUserResult.get(0).getIsDelete()!=0)//sudah terdaftar dan aktif
                {
                    return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_EMAIL_ISEXIST,
                            HttpStatus.NOT_ACCEPTABLE,null,"FV01001",request);
                }
                User nextUser = listUserResult.get(0);
                nextUser.setPassword(BcryptImpl.hash(userz.getPassword()));
                nextUser.setToken(BcryptImpl.hash(String.valueOf(intVerification)));
                nextUser.setTokenCounter(nextUser.getTokenCounter()+1);//setiap kali mencoba ditambah 1

            }
            else//belum terdaftar
            {
                userz.setPassword(BcryptImpl.hash(userz.getPassword()));
                userz.setToken(BcryptImpl.hash(String.valueOf(intVerification)));
                userRepo.save(userz);
            }
//            String [] strEmail = {userz.getEmail()};
//            SMTPCore sc = new SMTPCore();
//            ConfigProperties.getEmailPassword();
//            System.out.println(sc.sendMailWithAttachment(strEmail,
//                    "DEMO REGISTRATION -- TOKEN : "+intVerification,
//                    new ReadTextFileSB("\\data\\template-BCAF.html").getContentFile(),
//                    "SSL",
//                    new String[] {ResourceUtils.getFile("classpath:\\data\\sample.docx").getAbsolutePath()}));
            System.out.println("VERIFIKASI -> "+intVerification);
        }catch (Exception e)
        {
            strExceptionArr[1]="checkRegis(User userz) --- LINE 70";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_REGIS_FAILED,
                    HttpStatus.NOT_FOUND,null,"FE01001",request);
        }

        return new ResponseHandler().generateModelAttribut(ConstantMessage.SUCCESS_CHECK_REGIS,
                HttpStatus.CREATED,null,null,request);
    }

    public Map<String,Object> confirmRegis(User userz, String emails, WebRequest request) {
        List<User> listUserResult = userRepo.findByEmail(emails);
        try
        {
            if(listUserResult.size()!=0)
            {
                User nextUser = listUserResult.get(0);
                if(!BcryptImpl.verifyHash(userz.getToken(),nextUser.getToken()))
                {
                    return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_TOKEN_NOT_VALID,
                            HttpStatus.NOT_ACCEPTABLE,null,"FV01002",request);
                }
                nextUser.setIsDelete((byte) 1);//SET REGISTRASI BERHASIL
            }
            else
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_USER_NOT_EXISTS,
                        HttpStatus.NOT_FOUND,null,"FV01003",request);
            }
        }
        catch (Exception e)
        {
            strExceptionArr[1]="confirmRegis(User userz)  --- LINE 103";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_REGIS_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR,null,"FE01002",request);
        }

        return new ResponseHandler().generateModelAttribut(ConstantMessage.SUCCESS_CHECK_REGIS,
                HttpStatus.OK,null,null,request);
    }

    public Map<String,Object> doLogin(User userz,WebRequest request) {
//        List<User> listUserResult = userRepo.findByEmail(userz.getEmail());
        List<User> listUserResult = userRepo.findByEmailOrUsername(userz.getEmail(),userz.getUsername());
        try
        {
            if(listUserResult.size()!=0)
            {
                User nextUser = listUserResult.get(0);
                if(!BcryptImpl.verifyHash(userz.getPassword(),nextUser.getPassword()))
                {
                    return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_LOGIN_FAILED,
                            HttpStatus.NOT_ACCEPTABLE,null,"FV01004",request);
                }
                nextUser.setIsDelete((byte) 1);
            }
            else
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_USER_NOT_EXISTS,
                        HttpStatus.NOT_ACCEPTABLE,null,"FV01004",request);
            }
        }
        catch (Exception e)
        {
            strExceptionArr[1]="doLogin(User userz,WebRequest request)  --- LINE 132";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_LOGIN_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR,null,"FE01003",request);
        }

        return new ResponseHandler().generateModelAttribut(ConstantMessage.SUCCESS_LOGIN,
                HttpStatus.OK,null,null,request);
    }
}
