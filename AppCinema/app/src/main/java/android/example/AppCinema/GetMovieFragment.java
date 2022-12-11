package android.example.AppCinema;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GetMovieFragment extends Fragment {
    private NavController navController;
    TextView recommendedFilm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        navController= NavHostFragment.findNavController(this);
        View rootView = inflater.inflate(R.layout.get_movie_fragment, container, false);

        recommendedFilm = rootView.findViewById(R.id.tv_movie);
        setResultToTextViewFromBundle();

        Button button_back = rootView.findViewById(R.id.b_back_button_f4);
        button_back.setOnClickListener(view -> navController.popBackStack());
        return rootView;
    }

    private void setResultToTextViewFromBundle() {
        Bundle bundle = getArguments();
            String result = bundle.getString("movie");
            if (!result.isEmpty()) recommendedFilm.setText(result);
            else recommendedFilm.setText("Error: Please enter correct name of movie");
    }
}