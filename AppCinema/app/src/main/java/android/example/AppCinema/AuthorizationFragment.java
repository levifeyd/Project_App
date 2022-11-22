package android.example.AppCinema;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AuthorizationFragment extends Fragment {
    NavController navController;
    EditText mail;
    EditText password;
    HashMap<String, String> dataBaseUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.authorization_fragment, container, false);
        navController= NavHostFragment.findNavController(this);
        dataBaseUsers = new HashMap<>();

        containUserInfoDataBase(dataBaseUsers);

        mail = rootView.findViewById(R.id.login);
        password = rootView.findViewById(R.id.password);
        password.setTransformationMethod(new PasswordTransformationMethod());

        Button button_next = rootView.findViewById(R.id.sign_in);
        button_next.setOnClickListener(view -> authorization());

        Button button_back = rootView.findViewById(R.id.button_back);
        button_back.setOnClickListener(view -> navController.popBackStack());

        return rootView;
    }

    private void containUserInfoDataBase(HashMap<String, String> dataBaseUsers) {
        dataBaseUsers.put("Qwerty1", "qwerty@mail.ru");
        dataBaseUsers.put("Asdfgh1", "asdfgh@mail.ru");
        dataBaseUsers.put("Zxcvbn1", "zxcvbn@mail.ru");
    }

    private boolean containDigit(String src) {
        char[] array = src.toCharArray();  // fix
        boolean result = false;
        for (int i = 0; i < src.length(); i++) {
            if (array[i] >= 48 && array[i] <= 57) result = true;
        }
//        boolean result = false;
//        for (int i = 0 ; i < src.length(); i++) {
//            if (src.getIndex())
//        }
        return result;
    }

    private boolean containUpRegister(String src) {
        char[] array = src.toCharArray();  // getIndex() refactor
        boolean result = false;
        for (int i = 0; i < src.length(); i++) {
            if (array[i] >= 65 && array[i] <= 90) result = true;
        }
        return result;
    }

    private void showToast () {
        Activity activityObj = this.getActivity();
        @SuppressLint("ShowToast")
        Toast toast = Toast.makeText(activityObj, "Incorrect password", Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean checkContainInDataBaseUser(String pass, String email) {
        boolean result = false;
        if (pass.length() > 5 && containDigit(pass) && containUpRegister(pass) &&
                (dataBaseUsers.containsKey(pass) && (dataBaseUsers.get(pass) != null)))
           result = true;
        return result;
    }

    private void authorization() {
        String passString = password.getText().toString();
        String loginString = mail.getText().toString();
        if (checkContainInDataBaseUser(passString, loginString)) {
            navController.navigate(R.id.fragment2);
            setNullOnFieldPassAndMail();
        } else {
            showToast();
        }
    }

    private void setNullOnFieldPassAndMail(){
        mail.setText("");
        password.setText("");
    }
}