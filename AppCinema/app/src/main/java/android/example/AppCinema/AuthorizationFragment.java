package android.example.AppCinema;

import android.app.Activity;
import android.example.AppCinema.dataBase.MyDbManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class AuthorizationFragment extends Fragment {
    private NavController navController;
    private EditText mail;
    private EditText password;
    private MyDbManager myDbManager;

    private RelativeLayout root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.authorization_fragment, container, false);
        navController = NavHostFragment.findNavController(this);
        initButtonsMailPass(rootView);

        myDbManager = new MyDbManager(getContext());
        myDbManager.openDb();

        Button signIn = rootView.findViewById(R.id.buttomSignIn);
        signIn.setOnClickListener(view -> authorization());

        Button button_back = rootView.findViewById(R.id.text_button_back);
        button_back.setOnClickListener(view -> navController.popBackStack());

        Button button_register = rootView.findViewById(R.id.buttomRegister);
        button_register.setOnClickListener(view -> createUsers());
        return rootView;
    }

    private void initButtonsMailPass(View rootView) {
        root = rootView.findViewById(R.id.root_element);
        mail = rootView.findViewById(R.id.login);
        password = rootView.findViewById(R.id.pass);
        password.setTransformationMethod(new PasswordTransformationMethod());
    }

    private boolean containDigit(String src) {
        for (int i = 0; i < src.length(); i++) {
            if (Character.isDigit(src.charAt(i))) return true;
        }
        return false;
    }

    private boolean containUpRegister(String src) {
        for (int i = 0; i < src.length(); i++) {
            if (Character.isUpperCase(src.charAt(i))) return true;
        }
        return false;
    }

    private void showToastLoginOrPass() {
        Activity activityObj = this.getActivity();
        Toast toast = Toast.makeText(activityObj, "Incorrect password or login", Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean checkContainInDataBase(String password, String email) {
        return true;
//        String passwordFromDb = myDbManager.getFromDb(email);
//        if (passwordFromDb != null)
//            return passwordFromDb.equals(password);
//        else return false;
    }

    private void authorization() {
        if (checkContainInDataBase(password.getText().toString(), mail.getText().toString())) {
            navController.navigate(R.id.fragment3);
            setNullOnFieldPassAndMail();
        } else {
            showToastLoginOrPass();
        }
    }

    private void setNullOnFieldPassAndMail() {
        mail.setText("");
        password.setText("");
    }

    private void createUsers() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext()); // context it this argument (getContext)=requireContext
        dialog.setTitle("Registration");
        dialog.setMessage("Please enter your email, password and name." + "\nPassword must contain letters, numbers and capital letters!");
        LayoutInflater inflater = LayoutInflater.from(getContext()); // создаем объект и работаем с этим же классом( this)
        View register_window = inflater.inflate(R.layout.registration_layout, null);  // получаем шаблон , который на основе класса View
        dialog.setView(register_window); // установливаем внутренний шаблон

        EditText email_new = register_window.findViewById(R.id.emailField);
        EditText pass_new = register_window.findViewById(R.id.passField);
        pass_new.setTransformationMethod(new PasswordTransformationMethod());

        dialog.setNegativeButton("Cancel", (dialogInterface, which) -> dialogInterface.dismiss());
        dialog.setPositiveButton("Create", (dialogInterface, which) -> clickCreate(email_new, pass_new));
        dialog.show();
    }

    private boolean checkEmail(String email_new) {
        boolean result = false;
        int flag = 0;
        for (int i = 0; i < email_new.length(); i++) {
            if (email_new.charAt(i) == '@' || email_new.charAt(i) == '.') flag++;
        }
        if (flag == 2) result = true;
        return result;
    }

    private boolean checkPassword(String pass_new) {
        return containDigit(pass_new) && containUpRegister(pass_new) && (!pass_new.isEmpty());
    }

    private void clickCreate(EditText email_new, EditText pass_new) {
        if (!checkEmail(email_new.getText().toString())) {
            Snackbar.make(root, "Please enter correct email", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (!checkPassword(pass_new.getText().toString())) {
            Snackbar.make(root, "Please enter correct password", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (myDbManager.getFromDb(email_new.getText().toString()) == null) {
            myDbManager.insertToDb(email_new.getText().toString(), pass_new.getText().toString());
            Snackbar.make(root, "User create !", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(root, "User with same email already exist!", Snackbar.LENGTH_SHORT).show();
        }

    }
}
