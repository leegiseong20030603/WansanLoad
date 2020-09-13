package leegiseong.core.wansan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Intro extends AppCompatActivity {

    String[] SharedPreferencesList;
    String id, pw;
    Interface _interface;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        retrofit = new RetrofitConfig().getRetrofit();
        _interface = retrofit.create(Interface.class);
        SharedPreferencesList = getResources().getStringArray(R.array.sharedpreferences);
        final String SharedPreferencesID = SharedPreferencesList[0];
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesID,0);
        id = sharedPreferences.getString(SharedPreferencesList[1],"");
        pw = sharedPreferences.getString(SharedPreferencesList[2],"");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id.isEmpty() || pw.isEmpty()){
                    Intent login = new Intent(Intro.this, Login.class);
                    startActivity(login);
                    finish();
                }else{
                    _interface.User_Check(id, pw).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            if (user.getResponse()){

                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }

            }
        },4000);
    }
}