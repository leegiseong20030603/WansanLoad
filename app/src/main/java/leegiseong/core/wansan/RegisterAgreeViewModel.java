package leegiseong.core.wansan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterAgreeViewModel extends ViewModel {
    MutableLiveData<String> agree, phoneNumber;

    public MutableLiveData<String> getAgree(){
        if (agree == null){
            agree = new MutableLiveData<>();
        }
        return agree;
    }
}
