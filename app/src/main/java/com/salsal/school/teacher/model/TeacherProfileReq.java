package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

public class TeacherProfileReq {


    /**
     * name : sepehr
     * birth_date : 1369-10-11
     * email : aliahmadi@gmail.com
     * phone_number : 09363531363
     * national_code : 4900191051
     */

    @SerializedName("birth_date")
    private long birthDate;
    @SerializedName("email")
    private String email;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("education")
    private String education;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
