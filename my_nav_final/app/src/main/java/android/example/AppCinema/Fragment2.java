package android.example.my_nav_final;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment2 extends Fragment {
    NavController navController;


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_2, container, false);

        Button button_next = (Button) rootView.findViewById(R.id.b_films);
        Button button_back = (Button) rootView.findViewById(R.id.b_back_f2);
        navController= NavHostFragment.findNavController(this);

        button_next.setOnClickListener(view -> navController.navigate(R.id.fragment3));
        button_back.setOnClickListener(view -> navController.popBackStack());

        return rootView;
    }
}