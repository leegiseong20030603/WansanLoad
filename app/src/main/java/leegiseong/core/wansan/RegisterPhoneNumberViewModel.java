package leegiseong.core.wansan;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterPhoneNumberViewModel extends ViewModel {

    MutableLiveData<String> phoneNumber;

    public MutableLiveData<String> getPhoneNumber(){
        if (phoneNumber == null){
            phoneNumber = new MutableLiveData<>();
        }
        return phoneNumber;
    }
}
