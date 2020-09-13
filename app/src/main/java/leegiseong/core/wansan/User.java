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

    @SerializedName("name") private String name; // 사용자 이름
    @Expose

    @SerializedName("phone") private String phone; // 사용자 전화번호
    @Expose

    @SerializedName("gradeID") private String gradeID; // 사용자 학번
    @Expose

    @SerializedName("email") private String email; // 사용자 이메일
    @Expose

    @SerializedName("lastConnect") private String lastConnect; // 사용자 최근 접속
    @Expose

    @SerializedName("createTime") private String createTime; // 사용자 생성 날짜
    @Expose

    @SerializedName("agree") private String agree; // 사용자 동의약관

    private static User user;

    public User(String id, String pw, String name, String phone, String email, String agree, String lastConnect, String gradeID, String createTime){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.agree = agree;
        this.lastConnect = lastConnect;
        this.gradeID = gradeID;
        this.createTime = createTime;
    }

    public User inputInstance(String id, String pw, String name, String phone, String email, String agree, String lastConnect, String gradeID, String createTime){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.agree = agree;
        this.lastConnect = lastConnect;
        this.gradeID = gradeID;
        this.createTime = createTime;
        return user;
    }

    public User instance(){
        return user;
    }

    public Boolean getResponse() {
        return response;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getGradeID() {
        return gradeID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastConnect() {
        return lastConnect;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getAgree() {
        return agree;
    }

    public static User getUser() {
        return user;
    }

    public String getPhone() {
        return phone;
    }
}
