package com.salsal.school.teacher.interfaces;

import android.app.Notification;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.salsal.school.teacher.model.ClsNotification;
import com.salsal.school.teacher.model.LoginReq;
import com.salsal.school.teacher.model.LoginResponse;
import com.salsal.school.teacher.model.TeacherProfileResponce;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Sepehr on 1/25/2017.
 */

public interface ApiInterface {


    // String PRE_URL = "api.salsal.local";
    String PRE_URL = "192.168.43.114:80";
    String URL_V1 = "/api/v1";

    @POST(URL_V1 + "/user/signin")
    Call<LoginResponse> loginUser(@Body LoginReq req);

    @GET(URL_V1 + "/teacherProfile/{id}")
    Call<TeacherProfileResponce> getTeacherProfile(@Path("id") String id, @Query("token") String token);

    @POST(PRE_URL + "/teacher")
    Call<JsonObject> updateProfile(@Query("user_id") String userId);

    @GET(URL_V1 + "/notification")
    Call<ClsNotification> getNotifications(@Query("token") String token);

    @POST(PRE_URL + "/getdelegates")
    @FormUrlEncoded
    Call<JsonObject> getDelegates(@Header("token") String token,
                                  @Field("event_id") String eventId,
                                  @Field("page") int pageNumber,
                                  @Field("perpage") int perPage);

    @POST(PRE_URL + "/searchdelegate")
    @FormUrlEncoded
    Call<JsonObject> searchDelegate(@Header("token") String token,
                                    @Field("search") String query,
                                    @Field("event_id") String eventId,
                                    @Field("page") int pagenumber,
                                    @Field("perpage") int perPage);

    @POST(PRE_URL + "/getagendas")
    @FormUrlEncoded
    Call<JsonObject> getAgendas(@Header("token") String token,
                                @Field("event_id") String eventId,
                                @Field("page") int pageNumber,
                                @Field("perpage") int perPage);

    @POST(PRE_URL + "/getevent")
    @FormUrlEncoded
    Call<JsonObject> getEvent(@Header("token") String token,
                              @Field("event_id") String eventId);

    @POST(PRE_URL + "/getmessages")
    @FormUrlEncoded
    Call<JsonObject> getMessages(@Field("page") int pageNumber,
                                 @Header("token") String uid,
                                 @Field("perpage") int perPage);


    @POST(PRE_URL + "/setbookmark")
    @FormUrlEncoded
    Call<JsonObject> setBookmark(@Header("token") String uid,
                                 @Field("id") String eventId,
                                 @Field("type") int type);

    @POST(PRE_URL + "/resetpassword")
    @FormUrlEncoded
    Call<JsonObject> resetPassword(@Field("username") String userName);

    @Multipart
    @POST(PRE_URL + "/updateprofile")
    Call<JsonObject> updateProfile(@Header("token") String token,
                                   @PartMap() Map<String, RequestBody> partMap,
                                   @Part MultipartBody.Part file);

    @GET(PRE_URL)
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<JsonObject> getGoogleRouts(@Query("origin") String source, @Query("destination") String destination, @Query("key") String apikey);

}