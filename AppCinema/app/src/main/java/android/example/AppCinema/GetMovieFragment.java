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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    private String URLImage;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.get_movie_fragment, container, false);

        navController= NavHostFragment.findNavController(this);
        recommendedFilm = rootView.findViewById(R.id.tv_movie);
        imageView = rootView.findViewById(R.id.imageView_movie);
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
            if (response != null && !response.equals("")) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("docs");
                    while ((name == null || name.equals("null")) || (URLImage.equals("null"))) {
                        int position = (int) (Math.random() * jsonArray.length());
                        JSONObject movieInfo = jsonArray.getJSONObject(position);
                        name = movieInfo.getString("alternativeName");

                        JSONObject poster = movieInfo.getJSONObject("poster");
                        URLImage = poster.getString("url");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recommendedFilm.setText(name);
                Picasso.with(getContext())
                        .load(URLImage)
                        .placeholder(R.drawable.ic_baseline_arrow_circle_down_24)
                        .error(R.drawable.ic_baseline_block_24)
                        .into(imageView);
            } else {
                recommendedFilm.setText("Error: Internet access is absent");
            }
        }
    }

    private void getFilters() {
        Bundle bundle = getArguments();
        sinceYear = bundle.getString("sinceYearString");
        untilYear = bundle.getString("untilYearString");
        category = bundle.getString("movieOrSerial");
    }
}

// create new table with fileds user and movie

//ID  movie
