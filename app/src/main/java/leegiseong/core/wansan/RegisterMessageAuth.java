package leegiseong.core.wansan;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterMessageAuth extends Fragment {

    RegisterViewModel viewModel;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    Button sendAuthCode, MessageAuth;
    EditText phoneNumber;
    LinearLayout authCodeContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_messageauth,container,false);
        sendAuthCode = view.findViewById(R.id.sendAuthCode);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        MessageAuth = view.findViewById(R.id.MessageAuth);
        authCodeContainer = view.findViewById(R.id.authCodeContainer);
        sendAuthCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getPhoneNumber = phoneNumber.getText().toString();
                if (getPhoneNumber.length() < 11){
                    Toast.makeText(view.getContext(), "전화번호을 옳바르게 넣어주세요.",Toast.LENGTH_LONG).show();
                }else{
                    String formatNumberToE164 = PhoneNumberUtils.formatNumberToE164(getPhoneNumber,"KR");
                    Log.d("phone", formatNumberToE164);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            formatNumberToE164,
                            30,
                            TimeUnit.SECONDS,
                            getActivity(),
                            mCallbacks
                    );
                    authCodeContainer.setVisibility(View.VISIBLE);
                }
            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                Toast.makeText(getActivity(),"인증번호 요청 시간이 초과되었습니다. \n인증번호을 재전송해주세요",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String SMS_CODE = phoneAuthCredential.getSmsCode();
                Log.d("RegisterMessageAuth : ", SMS_CODE);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w("TAG", e.getMessage(),e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {

                Log.d("TAG", "onCodeSent:" + verificationId);
            }
        };
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new RegisterViewModel();
    }
}
