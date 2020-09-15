package leegiseong.core.wansan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AgreeInformation extends AppCompatActivity {

    String agreeInformationTitle;
    TextView getAgreeInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agree_information);

        getAgreeInformation = findViewById(R.id.getAgreeInformation);
        agreeInformationTitle = getResources().getString(R.string.agreeInformationTitle);
        Intent getAgreeInformationIntent = getIntent();
        String getAgreeInformationIntentStringExtra = getAgreeInformationIntent.getStringExtra(agreeInformationTitle);

        getAgreeInformation.setText(getAgreeInformationIntentStringExtra);

    }
}