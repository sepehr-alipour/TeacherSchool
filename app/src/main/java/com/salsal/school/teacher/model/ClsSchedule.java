package com.salsal.school.teacher.model;

import java.util.List;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class ClsSchedule {

    private int id;
    private List<Days> Days;

    public List<Days> getDays() {
        return Days;
    }

    public void setDays(List<Days> Days) {
        this.Days = Days;
    }

    public static class Days {

        private int Id;
        private String Title;
        private List<ClsLecture> Lectures;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public List<ClsLecture> getLectures() {
            return Lectures;
        }

        public void setLectures(List<ClsLecture> Lectures) {
            this.Lectures = Lectures;
        }

    }
}
