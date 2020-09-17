package leegiseong.core.wansan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class RegisterInputUserInformation extends Fragment {

    RegisterAgreeViewModel agreeViewModel;
    RegisterPhoneNumberViewModel phoneNumberViewModel;
    String phoneNumber, agree;
    Button registerUser;
    String TAG = "RVM_Value";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_user,container,false);
        registerUser = view.findViewById(R.id.registerUser);
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,agree);
                Log.d(TAG,phoneNumber);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        phoneNumberViewModel = ViewModelProviders.of(getActivity()).get(RegisterPhoneNumberViewModel.class);
        agreeViewModel = ViewModelProviders.of(getActivity()).get(RegisterAgreeViewModel.class);
        phoneNumber = phoneNumberViewModel.getPhoneNumber().getValue();
        agree = agreeViewModel.getAgree().getValue();
    }
}
