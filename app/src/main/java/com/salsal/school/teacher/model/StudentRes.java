package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class StudentRes {

    /**
     * msg : ok
     * data : [{"id":1,"name":null,"birth_date":null,"image_url":null,"email":null,"phone_number":null,"national_code":null,"user_id":26,"parent_id":null,"created_at":"2018-05-18 15:44:50","updated_at":"2018-05-18 15:44:50","pivot":{"academic_class_id":1,"student_id":1,"created_at":"2018-05-18 15:54:14","updated_at":"2018-05-18 15:54:14"}},{"id":2,"name":null,"birth_date":null,"image_url":null,"email":null,"phone_number":null,"national_code":null,"user_id":27,"parent_id":null,"created_at":"2018-05-18 15:45:07","updated_at":"2018-05-18 15:45:07","pivot":{"academic_class_id":1,"student_id":2,"created_at":"2018-05-18 15:54:14","updated_at":"2018-05-18 15:54:14"}},{"id":3,"name":null,"birth_date":null,"image_url":null,"email":null,"phone_number":null,"national_code":null,"user_id":28,"parent_id":null,"created_at":"2018-05-18 15:45:19","updated_at":"2018-05-18 15:45:19","pivot":{"academic_class_id":1,"student_id":3,"created_at":"2018-05-18 15:54:14","updated_at":"2018-05-18 15:54:14"}}]
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : null
         * birth_date : null
         * image_url : null
         * email : null
         * phone_number : null
         * national_code : null
         * user_id : 26
         * parent_id : null
         * created_at : 2018-05-18 15:44:50
         * updated_at : 2018-05-18 15:44:50
         * pivot : {"academic_class_id":1,"student_id":1,"created_at":"2018-05-18 15:54:14","updated_at":"2018-05-18 15:54:14"}
         */

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("birth_date")
        private String birthDate;
        @SerializedName("image_url")
        private String imageUrl;
        @SerializedName("email")
        private String email;
        @SerializedName("phone_number")
        private String phoneNumber;
        @SerializedName("national_code")
        private String nationalCode;
        @SerializedName("user_id")
        private int userId;
        @SerializedName("parent_id")
        private String parentId;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("pivot")
        private PivotBean pivot;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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

        public String getNationalCode() {
            return nationalCode;
        }

        public void setNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public PivotBean getPivot() {
            return pivot;
        }

        public void setPivot(PivotBean pivot) {
            this.pivot = pivot;
        }

        public static class PivotBean {
            /**
             * academic_class_id : 1
             * student_id : 1
             * created_at : 2018-05-18 15:54:14
             * updated_at : 2018-05-18 15:54:14
             */

            @SerializedName("academic_class_id")
            private int academicClassId;
            @SerializedName("student_id")
            private int studentId;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("updated_at")
            private String updatedAt;

            public int getAcademicClassId() {
                return academicClassId;
            }

            public void setAcademicClassId(int academicClassId) {
                this.academicClassId = academicClassId;
            }

            public int getStudentId() {
                return studentId;
            }

            public void setStudentId(int studentId) {
                this.studentId = studentId;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }
    }
}
