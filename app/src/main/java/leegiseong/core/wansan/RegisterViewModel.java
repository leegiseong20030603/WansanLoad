package leegiseong.core.wansan;

import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    String PhoneNumber;
    String Agree;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAgree() {
        return Agree;
    }

    public void setAgree(String agree) {
        Agree = agree;
    }
}
