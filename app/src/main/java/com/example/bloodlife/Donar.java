package com.example.bloodlife;

public class Donar{
private String name;
private String email;
private String div;
private String address;
private Integer phone;
private String gender;
private String BloodGroup;

    public String getBloodGroup(){
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup){
        BloodGroup = bloodGroup;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDiv(){
        return div;
    }

    public void setDiv(String div){
        this.div = div;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public Integer getPhone(){
        return phone;
    }

    public void setPhone(Integer phone){
        this.phone = phone;
    }

    public Donar(){
    }
}
