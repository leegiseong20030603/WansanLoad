package leegiseong.core.wansan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterInputUserInformation extends Fragment {

    RegisterViewModel viewModel;
    String phoneNumber, agree;
    String VIEWMODEL_TAG = "VIEWMODEL_TAG";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_user,container,false);
        agree = viewModel.getAgree();
        phoneNumber = viewModel.getPhoneNumber();
        Log.d(VIEWMODEL_TAG,agree);
        Log.d(VIEWMODEL_TAG, phoneNumber);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new RegisterViewModel();
    }
}
