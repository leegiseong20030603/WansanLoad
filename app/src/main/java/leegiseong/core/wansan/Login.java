package leegiseong.core.wansan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    TextInputEditText input_id, input_pw;
    EditText ID, PW;
    TextInputLayout id_layout, pw_layout;
    Button Register, Login;
    Retrofit retrofit;
    RetrofitConfig retrofitConfig;
    Interface Interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        input_id = findViewById(R.id.input_id);
        input_pw = findViewById(R.id.input_pw);
        id_layout = findViewById(R.id.id_layout);
        pw_layout = findViewById(R.id.pw_layout);
        Register = findViewById(R.id.Register);
        Login = findViewById(R.id.Login);
        retrofitConfig = new RetrofitConfig();
        retrofit = retrofitConfig.getRetrofit();
        Interface = retrofit.create(Interface.class);
        id_layout.setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.errorTextColor)));
        pw_layout.setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.errorTextColor)));
        ID = id_layout.getEditText();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getID = ID.getText().toString();
                String getPW = PW.getText().toString();
                if (getID.isEmpty()){
                    Toast.makeText(Login.this, "아이디를 넣어주세요.",Toast.LENGTH_LONG).show();
                }else if (getPW.isEmpty()){
                    Toast.makeText(Login.this, "비밀번호를 넣어주세요.",Toast.LENGTH_LONG).show();
                }else {
                    Interface.Login(getID, getPW).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User responseBody = response.body();
                            if (responseBody.getResponse()){
                                String[] TitleItem = getResources().getStringArray(R.array.sharedpreferences);
                                SharedPreferences sharedPreferences = getSharedPreferences(TitleItem[0],0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(TitleItem[1],responseBody.getId());
                                editor.putString(TitleItem[2],responseBody.getPw());
                                editor.apply();
                            }else {

                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register = new Intent(Login.this, Register.class);
                startActivity(Register);
            }
        });

        pw_layout.setPasswordVisibilityToggleEnabled(true);

        ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable string) {
                if (string.toString().contains(" ")){
                    id_layout.setError("공백은 넣을 수 없습니다.");
                }else {
                    id_layout.setError(null);
                }
            }
        });
    }
}