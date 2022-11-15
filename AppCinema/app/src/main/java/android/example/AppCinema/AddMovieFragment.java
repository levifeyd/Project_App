package android.example.AppCinema;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class AddMovieFragment extends Fragment {
    private NavController navController;
    private ArrayList<View> allMovies;
    private Button button_add;
    private Button randomButton;
    private Button deleteField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_movie_fragment, container, false);
        navController= NavHostFragment.findNavController(this);
        allMovies = new ArrayList<>();  //инициализировали наш массив с edittext
        final LinearLayout linear = rootView.findViewById(R.id.linear);

        button_add = rootView.findViewById(R.id.button_add);
        ClickOfButtonsOfCustomLayout(linear);

        randomButton = rootView.findViewById(R.id.button_random);
        ClickRandomButton();

        Button button_back = rootView.findViewById(R.id.b_back_button_f3);
        button_back.setOnClickListener(view -> navController.popBackStack());
        return rootView;
    }

    private void ClickOfButtonsOfCustomLayout(LinearLayout linear){  // в кастомном layout находим кнопки и делаем обработку нажатий
        button_add.setOnClickListener(v -> {
            @SuppressLint("InflateParams")
            final View view = getLayoutInflater().inflate(R.layout.custom_layout, null);  //берем наш кастомный лейаут находим через него все наши кнопки, задаем нужные данные
            deleteField = view.findViewById(R.id.button_delete);
            allMovies.add(view);  //добавляем все что создаем в массив
            linear.addView(view);  //добавляем елементы в linearlayout
            clickDelete(view);
        });
    }

    private void ClickRandomButton() {  // берем рандомную строку из списка строк и передадим ее в следующий фрагмент
        randomButton.setOnClickListener(view -> {
            if (allMovies.size() != 0) {
                sendStringToGetMovieFragment();
            } else {
                showToast();
            }
        });
    }

    private  void clickDelete(View view) {
        deleteField.setOnClickListener(v1 -> {
            try {
                ((LinearLayout) view.getParent()).removeView(view);  //получаем родительский view и удаляем его
                allMovies.remove(view); //удаляем эту же запись из массива что бы не оставалось мертвых записей
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void showToast () {
        Activity activityObj = this.getActivity();
        @SuppressLint("ShowToast")
        Toast toast = Toast.makeText(activityObj, "Please add movie", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void sendStringToGetMovieFragment() {
        int position = (int)(Math.random() * allMovies.size());
        String result = ((EditText) allMovies.get(position).findViewById(R.id.editText)).getText().toString();
        AddMovieFragment fragment3 = new AddMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putString("string", result);
        fragment3.setArguments(bundle);
        navController.navigate(R.id.fragment4, bundle);
    }
}