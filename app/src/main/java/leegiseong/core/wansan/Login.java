package leegiseong.core.wansan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputEditText input_id, input_pw;
    TextInputLayout id_layout, pw_layout;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        input_id = findViewById(R.id.input_id);
        input_pw = findViewById(R.id.input_pw);
        id_layout = findViewById(R.id.id_layout);
        pw_layout = findViewById(R.id.pw_layout);
        Register = findViewById(R.id.Register);

        id_layout.setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.errorTextColor)));
        pw_layout.setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.errorTextColor)));

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register = new Intent(Login.this, Register.class);
                startActivity(Register);
            }
        });
        EditText ID_EDIT = id_layout.getEditText();

        pw_layout.setPasswordVisibilityToggleEnabled(true);

        ID_EDIT.addTextChangedListener(new TextWatcher() {
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