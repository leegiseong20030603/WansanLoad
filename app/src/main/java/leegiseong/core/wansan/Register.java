package leegiseong.core.wansan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.zip.Inflater;

public class Register extends AppCompatActivity {

    NoSwipeableViewpager viewPager;
    StateProgressBar step_state_ProgressBar;
    Dialog dialog;
    private long backTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        step_state_ProgressBar = findViewById(R.id.Register_Step_State);
        viewPager = findViewById(R.id.Register_viewPager);
        RegisterViewPagerState ViewPagerAdapter = new RegisterViewPagerState(getSupportFragmentManager());

        String[] Register_Step = getResources().getStringArray(R.array.Register_Step);
        step_state_ProgressBar.setStateDescriptionData(Register_Step);
        step_state_ProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
        viewPager.setAdapter(ViewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        step_state_ProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                        break;
                    }
                    case 1: {
                        step_state_ProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        break;
                    }
                    case 2: {
                        step_state_ProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        break;
                    }
                    case 3:{
                        step_state_ProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backTime;
        if (0 <= gapTime && 2000 >= gapTime){
            super.onBackPressed();
        }else {
            backTime = curTime;
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.warning_back_box,null);
            builder.setView(view);
            Button Yes = view.findViewById(R.id.warningYes);
            Button No = view.findViewById(R.id.warningNo);
            Yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    finish();
                }
            });
            No.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }
}