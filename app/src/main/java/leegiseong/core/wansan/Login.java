package leegiseong.core.wansan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputEditText input_id, input_pw;
    TextInputLayout id_layout, pw_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        input_id = findViewById(R.id.input_id);
        input_pw = findViewById(R.id.input_pw);
        id_layout = findViewById(R.id.id_layout);
        pw_layout = findViewById(R.id.pw_layout);

        id_layout.setCounterEnabled(true);
        pw_layout.setCounterEnabled(true);
        pw_layout.setPasswordVisibilityToggleEnabled(true);
    }
}