package android.example.my_nav_final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class Fragment3 extends Fragment {
    private NavController navController;
    private Button buttonBack;
    private ArrayList<View> allMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_3, container, false);

        navController= NavHostFragment.findNavController(this);

        //инициализировали наш массив с edittext
        allMovies = new ArrayList<View>();
        //находим наш linear который у нас под кнопкой add edittext в activity_main.xml
        final LinearLayout linear = (LinearLayout) rootView.findViewById(R.id.linear);

        Button button_add = (Button) rootView.findViewById(R.id.button_add);
        button_add.setOnClickListener(v -> {
        //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные

            final View view = getLayoutInflater().inflate(R.layout.custom_layout, null);
            Button deleteField = (Button) view.findViewById(R.id.button_delete);
            EditText text = (EditText) view.findViewById(R.id.editText);
            text.setText("Some movie");
            //добавляем все что создаем в массив
            allMovies.add(view);
            //добавляем елементы в linearlayout
            linear.addView(view);


            deleteField.setOnClickListener(v1 -> {
                try {
                    //получаем родительский view и удаляем его
                    ((LinearLayout) view.getParent()).removeView(view);
                    //удаляем эту же запись из массива что бы не оставалось мертвых записей
                    allMovies.remove(view);
                } catch(IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            });
        });

        Button showDataBtn = (Button) rootView.findViewById(R.id.button_random);
        showDataBtn.setOnClickListener(view -> {
            int position = (int)(Math.random() * allMovies.size());
            String result = ((EditText) allMovies.get(position).findViewById(R.id.editText)).getText().toString();
            Fragment3 fragment3 = new Fragment3();
            Bundle bundle = new Bundle();
            bundle.putString("string", result);
            fragment3.setArguments(bundle);
            navController.navigate(R.id.fragment4, bundle);
        });

        Button button_back = (Button) rootView.findViewById(R.id.b_back_button_f3);
        button_back.setOnClickListener(view -> navController.popBackStack());

        return rootView;
    }
}