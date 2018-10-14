package com.salsal.school.teacher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassRes {

    /**
     * msg : ok
     * data : [{"id":1,"title":"class_1","created_at":"2018-05-18 14:39:12","updated_at":"2018-05-18 14:39:12","pivot":{"academic_year_id":2,"class_room_id":1,"created_at":"2018-05-18 14:39:12","updated_at":"2018-05-18 14:39:12","id":1}},{"id":2,"title":"class_2","created_at":"2018-05-18 14:39:24","updated_at":"2018-05-18 14:39:24","pivot":{"academic_year_id":2,"class_room_id":2,"created_at":"2018-05-18 14:39:24","updated_at":"2018-05-18 14:39:24","id":2}}]
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
         * title : class_1
         * created_at : 2018-05-18 14:39:12
         * updated_at : 2018-05-18 14:39:12
         * pivot : {"academic_year_id":2,"class_room_id":1,"created_at":"2018-05-18 14:39:12","updated_at":"2018-05-18 14:39:12","id":1}
         */

        @SerializedName("class_id")
        private int id;
        @SerializedName("class_name")
        private String title;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("pivot")
        private PivotBean pivot;

        public DataBean(String title, int id) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
             * academic_year_id : 2
             * class_room_id : 1
             * created_at : 2018-05-18 14:39:12
             * updated_at : 2018-05-18 14:39:12
             * id : 1
             */

            @SerializedName("academic_year_id")
            private int academicYearId;
            @SerializedName("class_room_id")
            private int classRoomId;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("updated_at")
            private String updatedAt;
            @SerializedName("id")
            private int id;

            public int getAcademicYearId() {
                return academicYearId;
            }

            public void setAcademicYearId(int academicYearId) {
                this.academicYearId = academicYearId;
            }

            public int getClassRoomId() {
                return classRoomId;
            }

            public void setClassRoomId(int classRoomId) {
                this.classRoomId = classRoomId;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
