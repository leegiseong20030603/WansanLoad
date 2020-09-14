package leegiseong.core.wansan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class RegisterViewPagerState extends FragmentStatePagerAdapter {

    public RegisterViewPagerState(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                RegisterAgree registerAgree = new RegisterAgree();
                return registerAgree;
            }
            case 1:{
                RegisterMessageAuth registerMessageAuth = new RegisterMessageAuth();
                return registerMessageAuth;
            }
            case 2:{
                RegisterUser registerUser = new RegisterUser();
                return registerUser;
            }
            case 3:{
                RegisterResult registerResult = new RegisterResult();
                return registerResult;
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
