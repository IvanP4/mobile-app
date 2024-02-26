package com.example.mycontactlist;
import java.util.Calendar;
public class Contact {
    private int contactID;
    private String contactName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private String phoneNumeber;
    private String cellNumber;
    private String eMail;
    private Calendar birthday;

    public Contact(){
        contactID = -1;
        birthday = Calendar.getInstance();
    }
    public int getContactID(){
        return contactID;
    }
    public void setContactID(int i){
        contactID = i;
    }
    public String getContactName(){
        return contactName;
    }
    public void setContactName(String s){
    contactName = s;
    }
    public Calendar getBirthday(){
        return birthday;
    }
    public void setBirthday(Calendar c){
        birthday = c;

    }
    public String getStreetAddress(){
        return streetAddress;

    }
    public void setStreetAddress(String s){
        streetAddress = s;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String s){
        city = s;
    }
    public String getState(){
        return state;
    }
    public void setState(String s){
        state = s;
    }
    public String getZipcode(){
        return zipcode;
    }
    public void setZipCode(String s ){
        zipcode = s;
    }
    public void setPhoneNumber(String s){
        phoneNumeber = s;
    }
    public String getPhoneNumber(){
        return phoneNumeber;
    }
    public void setCellNumber(String s){
        cellNumber = s;
    }
    public String getCellNumber(){
        return cellNumber;
    }
    public void setEmail(String s){
        eMail = s;
    }
    public String getEMail(){
        return eMail;
    }
    }
