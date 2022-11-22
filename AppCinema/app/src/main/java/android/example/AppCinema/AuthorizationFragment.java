package android.example.AppCinema;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class AuthorizationFragment extends Fragment {
    NavController navController;
    EditText mail;
    EditText password;
    HashMap<String, String> dataBaseUsers;
    FrameLayout root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.authorization_fragment, container, false);
        navController = NavHostFragment.findNavController(this);
        dataBaseUsers = new HashMap<>();

        containUserInfoDataBase();

        root = rootView.findViewById(R.id.root);
        mail = rootView.findViewById(R.id.login);
        password = rootView.findViewById(R.id.password);
        password.setTransformationMethod(new PasswordTransformationMethod());

        Button button_next = rootView.findViewById(R.id.sign_in);
        button_next.setOnClickListener(view -> authorization());

        Button button_back = rootView.findViewById(R.id.button_back);
        button_back.setOnClickListener(view -> navController.popBackStack());

        Button button_registr = rootView.findViewById(R.id.registration);
        button_registr.setOnClickListener(view -> createUsers());

        return rootView;
    }

    private void containUserInfoDataBase() {
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

    private void showToast() {
        Activity activityObj = this.getActivity();
        @SuppressLint("ShowToast")
        Toast toast = Toast.makeText(activityObj, "Incorrect password", Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean checkContainInDataBaseUser(String pass) {
        return pass.length() > 5 && containDigit(pass)
                && containUpRegister(pass)
                && (dataBaseUsers.containsKey(pass) && (dataBaseUsers.get(pass) != null));
    }

    private void authorization() {
        String passString = password.getText().toString();
        if (checkContainInDataBaseUser(passString)) {
            navController.navigate(R.id.fragment2);
            setNullOnFieldPassAndMail();
        } else {
            showToast();
        }
    }

    private void setNullOnFieldPassAndMail() {
        mail.setText("");
        password.setText("");
    }

    private void createUsers() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext()); // context it this argument (getContext)=requireContext
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите все данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(getContext()); // создаем объект и работаем с этим же классом( this)

        View register_window = inflater.inflate(R.layout.registration_layout, null);  // получаем шаблон , который на основе класса View
        dialog.setView(register_window); // установливаем внутренний шаблон

        final EditText email_new = register_window.findViewById(R.id.emailField);
        final EditText pass_new = register_window.findViewById(R.id.passField);
        final EditText name_new = register_window.findViewById(R.id.nameField);
        dialog.show();

        dialog.setNegativeButton("Cancel", (dialogInterface, which) -> {
            dialogInterface.dismiss(); // dialog will close
        });

        dialog.setPositiveButton("Create", (dialogInterface, which) -> {
            if (TextUtils.isEmpty(email_new.getText().toString())) {
                Snackbar.make(root, "Please enter your email", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(name_new.getText().toString())) {
                Snackbar.make(root, "Please enter your name", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pass_new.getText().toString())) {
                Snackbar.make(root, "Please enter your password", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (!containDigit(pass_new.getText().toString()) || !containUpRegister(pass_new.getText().toString())) {
                Snackbar.make(root, "Please enter your password, more than 5 symbol and contain  at least one number", Snackbar.LENGTH_SHORT).show();
                return;
            }
            /// registration
            dataBaseUsers.put(pass_new.getText().toString(), email_new.getText().toString());
            Snackbar.make(root, "User create !", Snackbar.LENGTH_SHORT).show();
        });
    }
}