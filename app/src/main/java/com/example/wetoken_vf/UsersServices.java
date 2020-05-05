package com.example.wetoken_vf;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersServices {


    @FormUrlEncoded
    @POST("/api/users/register")
    Call<Reponse> register(@Field("MAIL") String MAIL,
                           @Field("PASSWORD") String PASSWORD,
                           @Field("TELEPHONE") String TELEPHONE,
                           @Field("NOM") String NOM,
                           @Field("ACTIF") boolean ACTIF,
                           @Field("PROFIL") String PROFIL,
                           @Field("LIB") String LIB,
                           @Field("EOS_NAME") String EOS_NAME,
                           @Field("AGENCE") String AGENCE
    );

    @FormUrlEncoded
    @POST("/api/auth/login")
    Call<Reponse> login(@Field("email") String MAIL,
                           @Field("password") String PASSWORD
    );




}
