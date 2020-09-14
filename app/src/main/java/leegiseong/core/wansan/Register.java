package leegiseong.core.wansan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class Register extends AppCompatActivity {

    NoSwipeableViewpager viewPager;
    StateProgressBar step_state_ProgressBar;

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
}