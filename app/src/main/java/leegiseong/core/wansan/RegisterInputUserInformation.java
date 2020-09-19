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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Retrofit;

public class RegisterInputUserInformation extends Fragment {

    RegisterAgreeViewModel agreeViewModel;
    RetrofitConfig retrofitConfig;
    Retrofit retrofit;
    Interface retrofitInterface;
    RegisterPhoneNumberViewModel phoneNumberViewModel;
    TextInputEditText registerInputID, registerInputPW, registerInputREPW, registerInputName, registerInputGrade;
    TextInputLayout registerLayoutID, registerLayoutPW, registerLayoutREPW, registerLayoutName, registerLayoutGrade;
    String phoneNumber, agree;
    Button registerUser;
    String TAG = "RVM_Value";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_user,container,false);
        registerUser = view.findViewById(R.id.registerUser);
        registerInputID = view.findViewById(R.id.registerInputID);
        registerInputPW = view.findViewById(R.id.registerInputPW);
        registerInputREPW = view.findViewById(R.id.registerInputREPW);
        registerInputName = view.findViewById(R.id.registerInputName);
        registerInputGrade = view.findViewById(R.id.registerInputGrade);
        registerLayoutID = view.findViewById(R.id.registerLayoutID);
        registerLayoutPW = view.findViewById(R.id.registerLayoutPW);
        registerLayoutREPW = view.findViewById(R.id.registerLayoutREPW);
        registerLayoutName = view.findViewById(R.id.registerLayoutName);
        registerLayoutGrade = view.findViewById(R.id.registerLayoutGrade);
        retrofit = retrofitConfig.getRetrofit();
        retrofitInterface = retrofit.create(Interface.class);
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = registerInputID.getText().toString();
                String PW = registerInputPW.getText().toString();
                String rePW = registerInputREPW.getText().toString();
                String Name = registerInputName.getText().toString();
                String Grade = registerInputGrade.getText().toString();
                if (ID.isEmpty() || PW.isEmpty() || rePW.isEmpty() || Name.isEmpty() || Grade.isEmpty()){
                    if (ID.isEmpty()){
                        registerLayoutID.setError("아이디를 넣어주세요!");
                        registerLayoutID.findFocus();
                    }else if (PW.isEmpty()){
                        registerLayoutPW.setError("비밀번호를 넣어주세요!");
                        registerLayoutID.setError(null);
                        registerLayoutPW.findFocus();
                    }else if (rePW.isEmpty()){
                        registerLayoutREPW.setError("확인 비밀번호를 넣어주세요!");
                        registerLayoutID.setError(null);
                        registerLayoutPW.setError(null);
                        registerLayoutREPW.findFocus();
                    }else if (Name.isEmpty()){
                        registerLayoutName.setError("이름을 넣어주세요!");
                        registerLayoutID.setError(null);
                        registerLayoutPW.setError(null);
                        registerLayoutREPW.setError(null);
                    }else if (Grade.isEmpty()){
                        registerLayoutName.setError("학번을 넣어주세요!");
                        registerLayoutID.setError(null);
                        registerLayoutPW.setError(null);
                        registerLayoutREPW.setError(null);
                        registerLayoutName.setError(null);
                    }
                }else {
                    registerLayoutID.setError(null);
                    registerLayoutPW.setError(null);
                    registerLayoutREPW.setError(null);
                    registerLayoutName.setError(null);
                    registerLayoutGrade.setError(null);
                    if (ID.length() < 6){
                        registerLayoutID.setError("아이디를 6자 이상 넣어주세요!");
                        registerLayoutID.findFocus();
                    }else if (PW.length() < 6){
                        registerLayoutPW.setError("비밀번호를 6자 이상 넣어주세요!");
                        registerLayoutID.setError(null);
                        registerLayoutPW.findFocus();
                    }else if (!PW.equals(rePW)){
                        registerLayoutREPW.setError("확인 비밀번호가 일치하지 않습니다.");
                        registerLayoutID.setError(null);
                        registerLayoutPW.setError(null);
                        registerLayoutREPW.findFocus();
                    }else if (Name.length() < 2){
                        registerLayoutName.setError("옳바른 이름을 넣어주세요.");
                        registerLayoutID.setError(null);
                        registerLayoutPW.setError(null);
                        registerLayoutREPW.setError(null);
                        registerLayoutName.findFocus();
                    }else if (Grade.length() < 5){
                        registerLayoutName.setError("옳바른 학번을 넣어주세요.");
                        registerLayoutID.setError(null);
                        registerLayoutPW.setError(null);
                        registerLayoutREPW.setError(null);
                        registerLayoutName.setError(null);
                        registerLayoutGrade.findFocus();
                    }else {
                        retrofitInterface.Register(ID, PW, Name, Grade, "010-3585-7458", agree);
                    }
                }
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
