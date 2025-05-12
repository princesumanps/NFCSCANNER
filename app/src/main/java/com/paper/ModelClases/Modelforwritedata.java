package com.paper.ModelClases;

import java.util.ArrayList;

public class Modelforwritedata {


    String customername,mobileno,Email,addressline1,addressline2,zipcode,customerid,Photo, Signature;

    public Modelforwritedata() {
    }

    public Modelforwritedata(String customername, String mobileno, String email, String addressline1, String addressline2, String zipcode, String customerid, String photo, String signature) {
        this.customername = customername;
        this.mobileno = mobileno;
        Email = email;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.zipcode = zipcode;
        this.customerid = customerid;
        this.Photo = photo;
        this.Signature = signature;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
}
