package android.example.AppCinema;

import static android.example.AppCinema.utils.NetworkUtils.generateURL;
import static android.example.AppCinema.utils.NetworkUtils.getResponseFromURL;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;

public class GetMovieFragment extends Fragment {
    private NavController navController;
    private TextView recommendedFilm;
    private String sinceYear;
    private String untilYear;
    private String category;
    private TextView descriptionMovie;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.get_movie_fragment, container, false);

        navController= NavHostFragment.findNavController(this);
        recommendedFilm = rootView.findViewById(R.id.tv_movie);
        descriptionMovie = rootView.findViewById(R.id.tv_movie_desc);
        getFilters();

        URL generatedURL = generateURL(sinceYear, untilYear, category);
        new QueryTask().execute(generatedURL);

        Button button_back = rootView.findViewById(R.id.b_back_button_f4);
        button_back.setOnClickListener(view -> navController.popBackStack());
        return rootView;
    }
    @SuppressLint("StaticFieldLeak")
    class QueryTask extends AsyncTask<URL, Void, String> {  //
        @Override
        protected String doInBackground(URL... urls) {  // рабоает в отдельном потоке и соверашем запрос
            String response = null;
            try {
                response = getResponseFromURL(urls[0]); // т.к переадем только одни юрл
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response; // возвращаем нашу строку
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String response) {  // получаем ответ от сервера
            String name = null;
            String desc = "";
            if (response != null && !response.equals("")) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("docs");
                    while (name == null || name == "null") {
                        int position = (int) (Math.random() * jsonArray.length());
                        JSONObject movieInfo = jsonArray.getJSONObject(position);
                        name = movieInfo.getString("alternativeName");
                        desc = movieInfo.getString("year");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recommendedFilm.setText(name);
                descriptionMovie.setText(desc);
            } else {
                recommendedFilm.setText("Error: Internet access is absent");
            }
        }
    }

    private void getFilters() {
        Bundle bundle = getArguments();
        sinceYear = bundle != null ? bundle.getString("sinceYearString") : "1900";
        untilYear = bundle != null ? bundle.getString("untilYearString") : "2022";
        category = bundle != null ? bundle.getString("movieOrSerial") : "movie";
    }
}