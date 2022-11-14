package android.example.AppCinema;

import android.example.AppCinema.R;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment4 extends Fragment {
    private NavController navController;
    private TextView recommendedFilm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController= NavHostFragment.findNavController(this);
        View rootView = inflater.inflate(R.layout.fragment_4, container, false);

        recommendedFilm = (TextView) rootView.findViewById(R.id.tv_movie);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String result = bundle.getString("string");
            recommendedFilm.setText(result);
        } else {
            recommendedFilm.setText("Error");
        }

        Button button_back = (Button) rootView.findViewById(R.id.b_back_button_f4);
        button_back.setOnClickListener(view -> navController.popBackStack());

        return rootView;
    }
}