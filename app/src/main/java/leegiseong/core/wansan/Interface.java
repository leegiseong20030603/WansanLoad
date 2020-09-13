package leegiseong.core.wansan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface Interface {

    // 사용자 회원가입
    @POST("Register.php")
    @FormUrlEncoded
    Call<User> Register
    (@Field("User_ID") String User_ID,
     @Field("User_PW") String User_PW,
     @Field("User_Name") String User_Name,
     @Field("User_Email") String User_Email,
     @Field("User_Birthday") String User_Birthday,
     @Field("User_Type") String User_Type,
     @Field("User_Agree") String User_Agree);

    // 사용자 로그인
    @POST("Login.php")
    @FormUrlEncoded
    Call<User> Login
    (@Field("User_ID") String User_ID,
     @Field("User_PW") String User_PW);

    // 앱 처음 시작할때 sharedPreferences 값에 저장되어있는 사용자 정보가 있으면 DB에 사용자가 가입되어있는지 확인하는 부분
    @POST("User_Check.php")
    @FormUrlEncoded
    Call<User> User_Check
    (@Field("User_ID") String User_ID,
     @Field("User_PW") String User_PW);

}
