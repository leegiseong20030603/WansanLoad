package leegiseong.core.wansan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterMessageAuth extends Fragment {

    RegisterPhoneNumberViewModel viewModel;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    Button sendAuthCode, MessageAuth;
    EditText phoneNumber, authCode;
    NoSwipeableViewpager registerViewpager;
    String SMS_CODE;
    Dialog dialog;
    String getPhoneNumber;
    PhoneAuthProvider.ForceResendingToken token;
    LinearLayout authCodeContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_messageauth,container,false);
        sendAuthCode = view.findViewById(R.id.sendAuthCode);
        sendAuthCode.setText("인증번호 전송");
        authCode = view.findViewById(R.id.authCode);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        MessageAuth = view.findViewById(R.id.MessageAuth);
        authCodeContainer = view.findViewById(R.id.authCodeContainer);
        registerViewpager = getActivity().findViewById(R.id.Register_viewPager);
        authCodeContainer.setVisibility(View.GONE);
        sendAuthCode.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPhoneNumber = phoneNumber.getText().toString();
                if (getPhoneNumber.length() < 11){
                    Toast.makeText(view.getContext(), "전화번호을 옳바르게 넣어주세요.",Toast.LENGTH_LONG).show();
                }else{
                    String formatNumberToE164 = PhoneNumberUtils.formatNumberToE164(getPhoneNumber, "KR");
                    viewModel.getPhoneNumber().setValue(formatNumberToE164);
                    if (authCodeContainer.getVisibility() == View.VISIBLE){
                        Log.d("MessageAuthResend", "MessageAuthResend");
                        resendVerificationCode(formatNumberToE164, token);
                    }else if (authCodeContainer.getVisibility() == View.GONE) {
                        Log.d("phone", formatNumberToE164);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                formatNumberToE164,
                                120,
                                TimeUnit.SECONDS,
                                getActivity(),
                                mCallbacks
                        );
                        authCodeContainer.setVisibility(View.VISIBLE);
                        sendAuthCode.setText("인증번호 재전송");
                    }
                }
            }
        });

        MessageAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getAuthCode = authCode.getText().toString();
                if (getAuthCode.length() == 6){
                    if (getAuthCode.equals(SMS_CODE)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View OKView = inflater.inflate(R.layout.ok, null);
                        builder.setView(OKView);
                        Button OK = OKView.findViewById(R.id.OK);
                        TextView messageTextView = OKView.findViewById(R.id.messageTextView);
                        messageTextView.setText("핸드폰 인증에 성공하셨습니다.");
                        OK.setText("확인");
                        OK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                registerViewpager.setCurrentItem(2);
                            }
                        });
                        dialog = builder.create();
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    }else {
                        Toast.makeText(getActivity(),"인증코드가 틀립니다.",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getActivity(),"인증코드 6자리을 넣어주세요",Toast.LENGTH_LONG).show();
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
                SMS_CODE = phoneAuthCredential.getSmsCode();
                authCode.setText(SMS_CODE);
                Log.d("RegisterMessageAuth : ", SMS_CODE);
                Log.d("RegisterMessageAuth P: ", phoneAuthCredential.getProvider());
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w("TAG", e.getMessage(),e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(getActivity(),"옳바르지 않는 전화번호입니다.\n   다시 입력해주세요",Toast.LENGTH_LONG).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(getActivity(), "비정상적인 많은 요청입니다.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken t) {
                Log.d("TAG", "onCodeSent:" + verificationId);
                token = t;
            }
        };
        return view;
    }

    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(RegisterPhoneNumberViewModel.class);
    }
}
