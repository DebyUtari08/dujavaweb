package com.juaracoding.dujavaweb.handler;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 27/02/2023 22:29
@Last Modified 27/02/2023 22:29
Version 1.0
*/

import com.juaracoding.dujavaweb.utils.ConstantMessage;

import java.util.regex.Pattern;

public class FormatValidation {

    public static void emailFormatValidation(String email) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_EMAIL_STANDARD_RFC5322)
                .matcher(email)
                .matches()))
        {
            throw new ResourceNotFoundException(ConstantMessage.ERROR_EMAIL_FORMAT_INVALID);
        }
    }

    public static void phoneNumberFormatValidation(String phoneNumner) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_PHONE)
                .matcher(phoneNumner)
                .matches()))
        {
            throw new ResourceNotFoundException(ConstantMessage.ERROR_PHONE_NUMBER_FORMAT_INVALID);
        }
    }

    public static void dateFormatYYYYMMDDValidation(String date) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_DATE_YYYYMMDD)
                .matcher(date)
                .matches()))
        {
            throw new ResourceNotFoundException(ConstantMessage.ERROR_DATE_FORMAT_YYYYMMDD);
        }
    }

    public static void dateFormatDDMMYYYYValidation(String date) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_DATE_DDMMYYYY)
                .matcher(date)
                .matches()))
        {
            throw new ResourceNotFoundException(ConstantMessage.ERROR_DATE_FORMAT_YYYYMMDD);
        }
    }

    public static Boolean emailFormatValidation(String email,String writeNull) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_EMAIL_STANDARD_RFC5322)
                .matcher(email)
                .matches()))
        {
            return false;
        }
        return true;
    }

    public static Boolean phoneNumberFormatValidation(String phoneNumner,String writeNull) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_PHONE)
                .matcher(phoneNumner)
                .matches()))
        {
            return false;
        }
        return true;
    }

    public static Boolean dateFormatYYYYMMDDValidation(String date, String writeNull) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_DATE_YYYYMMDD)
                .matcher(date)
                .matches()))
        {
            return false;
        }
        return true;
    }

//    public static boolean numericValidation(Object a)
//    {
//        if(a instanceof Double)if((Double)a==0.0) return false;
//        else if (a instanceof Integer)if((Integer)a==0)return false;
//        else if (a instanceof Float)if((Float)a==0.0)return false;
//        else if (a instanceof Long)if((Long)a==0) return false;
//        else if (a instanceof Short)if((Short)a==0) return false;
//        else if (a instanceof Byte)if((Byte)a==0) return false;
//
//        String x = a.toString();
//        if(x.equals("0.0") || x.equals("0"))return false;
//
//        int intK = 0123;
//        if(x.startsWith("0"))return false;
//
//
//
//        return true;
//    }
}

