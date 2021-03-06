package com.salsal.school.teacher.interfaces;

import com.google.gson.JsonObject;
import com.salsal.school.teacher.model.ActivityDetailsRes;
import com.salsal.school.teacher.model.ActivityReq;
import com.salsal.school.teacher.model.ActivityRes;
import com.salsal.school.teacher.model.AttendanceReq;
import com.salsal.school.teacher.model.AttendanceRes;
import com.salsal.school.teacher.model.ClassRes;
import com.salsal.school.teacher.model.NotificationRes;
import com.salsal.school.teacher.model.CourseRes;
import com.salsal.school.teacher.model.LoginReq;
import com.salsal.school.teacher.model.LoginRes;
import com.salsal.school.teacher.model.ScheduleRes;
import com.salsal.school.teacher.model.SchoolListRes;
import com.salsal.school.teacher.model.SendNotificationReq;
import com.salsal.school.teacher.model.NotificationDetailRes;
import com.salsal.school.teacher.model.StudentRes;
import com.salsal.school.teacher.model.TeacherProfileReq;
import com.salsal.school.teacher.model.TeacherProfileRes;
import com.salsal.school.teacher.model.UnitAdmins;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Sepehr on 1/25/2017.
 */

public interface ApiInterface {


    String URL_V1 = "/api/v1";

    @POST(URL_V1 + "/user/signin")
    Call<LoginRes> loginUser(@Body LoginReq req);

    @GET(URL_V1 + "/teacherProfile/{id}")
    Call<TeacherProfileRes> getTeacherProfile(@Path("id") String id, @Query("token") String token);


    @GET(URL_V1 + "/teacherProfile/classes")
    Call<ClassRes> getTeacherClasses(@Query("token") String token, @Query("course_id") int courseid);

    @GET(URL_V1 + "/activity/teacher")
    Call<ActivityRes> getTeacherActivities(@Query("token") String token, @Query("course_id") int courseid, @Query("class_id") int classid);

    @POST(URL_V1 + "/activity")
    Call<ActivityDetailsRes> createActivity(@Query("token") String token, @Body ActivityReq activityReq);

    @POST(URL_V1 + "/attendance")
    Call<AttendanceRes> attendanceStudent(@Query("token") String token, @Body AttendanceReq attendanceReq);

    @GET(URL_V1 + "/teacherProfile/courses")
    Call<CourseRes> getTecherCourses(@Query("token") String token);

    @PUT(URL_V1 + "/teacherProfile/{id}")
    Call<JsonObject> updateProfile(@Path("id") String userId, @Query("token") String token, @Body TeacherProfileReq teacherProfileReq);

    @GET(URL_V1 + "/studentProfile/classes")
    Call<StudentRes> getStudentsClass(@Query("token") String token, @Query("class_id") int classid);

    @GET(URL_V1 + "/notification")
    Call<NotificationRes> getNotifications(@Query("token") String token);

    //TODO
    @GET(URL_V1 + "/unitProfile/getAdmin")
    Call<UnitAdmins> getAdmins(@Query("token") String token);

    @GET(URL_V1 + "/notification/sentBox")
    Call<NotificationRes> getSentBoxNotifications(@Query("token") String token);

    @GET(URL_V1 + "/schedule/teacher")
    Call<ScheduleRes> getTeacherSchedule(@Query("token") String token);

    @GET(URL_V1 + "/notification/{id}")
    Call<NotificationDetailRes> getNotificationDetails(@Path("id") int notification, @Query("token") String token);

    @POST(URL_V1 + "/notification")
    Call<NotificationDetailRes> sendNotification(@Query("token") String token, @Body SendNotificationReq sendNotificationReq);

    @GET(URL_V1 + "/schools/name")
    Call<List<SchoolListRes>> getSchools(@Query("query") String query);

}