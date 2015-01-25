package com.syrine.com.syrine.api;

import com.syrine.com.syrine.model.ToknModel;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.mime.MultipartTypedOutput;

/**
 * Created by syrinetrabelsi on 24/01/15.
 */
public interface IWordpressService {


    @POST("/rest/v1.1/sites/syrine23.wordpress.com/media/new")
    void uploadPic(@Header("Authorization") String token, @Body MultipartTypedOutput photo, Callback<String> cb);

    @POST("/oauth2/authorize")
    void requestToken(@Query("client_id") String clientId, @Query("redirect_uri") String url, @Query("response_type") String code, @Query("blog") String urlBlog, Callback<Response> cb);

    @FormUrlEncoded
    @POST("/oauth2/token")
    void getToken(@Field("client_id") String clientId, @Field("redirect_uri") String url, @Field("client_secret") String clientSecret, @Field("code") String code, @Field("grant_type") String granType, Callback<ToknModel> cb);
}
