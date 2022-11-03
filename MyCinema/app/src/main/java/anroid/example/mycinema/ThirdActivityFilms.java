package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivityFilms extends AppCompatActivity {
    private Button changeNextActivity;
    private Button changeNextActivityBack;
    private EditText selectedFilm_1;
    private EditText selectedFilm_2;
    private EditText selectedFilm_3;
    private EditText selectedFilm_4;
    private EditText selectedFilm_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_acivity_films);

        changeNextActivity = findViewById(R.id.b_save);
        selectedFilm_1 = findViewById(R.id.et_textInput);
        selectedFilm_2 = findViewById(R.id.et_textInput_2);
        selectedFilm_3 = findViewById(R.id.et_textInput_3);
        selectedFilm_4 = findViewById(R.id.et_textInput_4);
        selectedFilm_5 = findViewById(R.id.et_textInput_5);

        changeNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String film = selectedFilms();
                if (film != null) {
                    Context context = ThirdActivityFilms.this;
                    Class destinationActivity = GetFilmActivity.class;
                    Intent GetFilmActivityChange = new Intent(context, destinationActivity);

                    GetFilmActivityChange.putExtra(Intent.EXTRA_TEXT, film);
                    startActivity(GetFilmActivityChange);
                } else {
                    Context context = ThirdActivityFilms.this;
                    Toast toast = Toast.makeText (context, "Please enter all position for movies", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            public String selectedFilms() {
                String[] films = new String[5];
                String result = null;
                int position = (int)(Math.random() * 5);

                films[0] = selectedFilm_1.getText().toString();
                films[1] = selectedFilm_2.getText().toString();
                films[2] = selectedFilm_3.getText().toString();
                films[3] = selectedFilm_4.getText().toString();
                films[4] = selectedFilm_5.getText().toString();

                if (notEmpty(films[0]) && notEmpty(films[1]) && notEmpty(films[2])
                        && notEmpty(films[3]) && notEmpty(films[4])) {
                    result = films[position];
                }
                return result;
            }
            public boolean notEmpty(String film) {
                return !(film.equals(""));
            }
        });

        changeNextActivityBack = findViewById(R.id.b_back);
        changeNextActivityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = ThirdActivityFilms.this;
                Class destinationActivity = SecondActivity.class;
                Intent SecondActivityChange = new Intent(context, destinationActivity);
                startActivity(SecondActivityChange);
            }
        });
    }
}