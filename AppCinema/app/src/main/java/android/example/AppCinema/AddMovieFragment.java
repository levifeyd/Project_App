package android.example.AppCinema;
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
import android.widget.Toast;


public class AddMovieFragment extends Fragment {
    private NavController navController;
    private String categoryMovie;
    private EditText yearSince;
    private EditText yearUntil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_movie_fragment, container, false);
        navController= NavHostFragment.findNavController(this);

        yearSince = rootView.findViewById(R.id.since_year);
        yearUntil = rootView.findViewById(R.id.until_year);
        Button movie = rootView.findViewById(R.id.radio_b_movie);
        Button serial = rootView.findViewById(R.id.radio_b_serial);

        movie.setOnClickListener(v -> categoryMovie = "movie");
        serial.setOnClickListener(v-> categoryMovie = "serial");

        Button randomButton = rootView.findViewById(R.id.button_random);
        randomButton.setOnClickListener(v -> navController.navigate(R.id.fragment4));

        Button getMovieButton = rootView.findViewById(R.id.button_get_movie);
        getMovieButton.setOnClickListener(v -> sendFilters());

        Button button_back = rootView.findViewById(R.id.b_back_button_f3);
        button_back.setOnClickListener(view -> navController.popBackStack());

        Button log_out = rootView.findViewById(R.id.b_back_log_out);
        log_out.setOnClickListener(v -> navController.popBackStack());
        return rootView;
    }

    private void sendFilters() {
        String sinceYearString = yearSince.getText().toString();
        String untilYearString = yearUntil.getText().toString();
        if (checkCategory()) {
            if (checkFilters(sinceYearString, untilYearString)) {  // if date of movie or serial correct
                Bundle bundle = new Bundle();
                bundle.putString("sinceYearString", sinceYearString);
                bundle.putString("untilYearString", untilYearString);
                bundle.putString("movieOrSerial", categoryMovie);

                AddMovieFragment fragment3 = new AddMovieFragment();
                fragment3.setArguments(bundle);
                navController.navigate(R.id.fragment4, bundle);
            } else {
                showToastDate();
            }
        } else {
            showToastCategory();
        }

    }
    private boolean checkFilters(String sinceYearString, String untilYearString) {  // example 1990-2002
        return checkLengthDate(sinceYearString, untilYearString)
                && (checkCorrectDate(sinceYearString, untilYearString))
                && (checkCorrectNumberDate(sinceYearString) &&checkCorrectNumberDate(untilYearString));
    }

    private boolean checkLengthDate(String sinceYearString, String untilYearString) {
        return sinceYearString.length() == 4 && untilYearString.length() == 4;
    }

    private boolean checkCorrectNumberDate(String src) { // if year is 20.. or 19....
        return ((src.charAt(0) == '2' && src.charAt(1) == '0') || (src.charAt(0) == '1' && src.charAt(1) =='9'));
    }

    private boolean checkCorrectDate(String startYear, String endYear) { // start <= end
        int start = Integer.parseInt(startYear);
        int end = Integer.parseInt(endYear);
        return start <= end;

    }

    private void showToastDate() {
        Activity activityObj = this.getActivity();
        Toast toast = Toast.makeText(activityObj, "Incorrect year of movie or serial\n" +
                "Example: 1999-2005", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void showToastCategory() {
        Activity activityObj = this.getActivity();
        Toast toast = Toast.makeText(activityObj, "Please choose category movie or serial",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean checkCategory() {
        return categoryMovie != null;
    }
}