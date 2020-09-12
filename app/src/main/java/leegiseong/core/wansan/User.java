package leegiseong.core.wansan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response") private Boolean response; // Retrofit2 결과값 가져오기
    @Expose

    @SerializedName("id") private String id; // 사용자 ID
    @Expose

    @SerializedName("pw") private String pw; // 사용자 PW
    @Expose

    @SerializedName("gradeID") private String gradeID; // 사용자 학번
    @Expose

    @SerializedName("name") private String name;

    @SerializedName("email") private String email; // 사용자 이메일
    @Expose

    @SerializedName("lastConnect") private String lastConnect; // 사용자 최근 접속
    @Expose

    @SerializedName("createTime") private String createTime; // 사용자 생성 날짜
    @Expose

    @SerializedName("agree") private String agree; // 사용자 동의약관

    private static User user;



    User(String id, String pw, String name, String email, String agree, String lastConnect, String gradeID, String createTime){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.agree = agree;
        this.lastConnect = lastConnect;
        this.gradeID = gradeID;
        this.createTime = createTime;
    }

    User instance(){
        return user;
    }
}
