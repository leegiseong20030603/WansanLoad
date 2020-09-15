package leegiseong.core.wansan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterAgree extends Fragment {

    CheckBox personalInformation, marketing;
    Button agree;
    Boolean personalInformationAgree, marketingAgree;
    TextView personalInformationText, marketingText;
    RegisterViewModel viewModel;
    NoSwipeableViewpager RegisterViewPager;
    String agreeInformationTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_agree,container,false);
        RegisterViewPager = getActivity().findViewById(R.id.Register_viewPager);
        personalInformationText = view.findViewById(R.id.personalInformationText);
        marketingText = view.findViewById(R.id.marketingText);
        personalInformationAgree = false;
        marketingAgree = false;
        personalInformation = view.findViewById(R.id.personalInformation);
        marketing = view.findViewById(R.id.marketing);
        String personalInformationString = getResources().getString(R.string.personalInformationAgreeText);
        String functionUseAgreeString = getResources().getString(R.string.functionUseAgreeText);
        personalInformationText.setText(personalInformationString +"\n\n" + functionUseAgreeString);
        agreeInformationTitle = getResources().getString(R.string.agreeInformationTitle);
        personalInformation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                personalInformationAgree = b;
            }
        });
        personalInformationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agreeInformation = new Intent(getActivity(), AgreeInformation.class);
                agreeInformation.putExtra(agreeInformationTitle, personalInformationText.getText().toString());
                startActivity(agreeInformation);
            }
        });
        marketing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                marketingAgree = b;
            }
        });
        agree = view.findViewById(R.id.agree);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personalInformationAgree){
                    String agree = personalInformationText.getText().toString()+"\n";
                    if (marketingAgree){
                        agree += marketingText.getText().toString();
                    }
                    viewModel.setAgree(agree);
                    RegisterViewPager.setCurrentItem(1);
                    Log.d("viewModel Agree", viewModel.getAgree());
                }else{
                    Toast.makeText(view.getContext(),"개인정보 동의약관을 체크해주세요.",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new RegisterViewModel();
    }
}
