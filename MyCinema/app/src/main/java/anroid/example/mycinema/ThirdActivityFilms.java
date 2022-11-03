package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivityFilms extends AppCompatActivity {
    private Button changeNextActivity;
    private Button changeNextActivityBack;
    private EditText selectedFilm_1;
    private EditText selectedFilm_2;
    private EditText selectedFilm_3;
    private String selectedFilm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_acivity_films);

        changeNextActivity = findViewById(R.id.b_save);
        selectedFilm_1 = findViewById(R.id.et_textInput);
        selectedFilm_2 = findViewById(R.id.et_textInput_2);
        selectedFilm_3 = findViewById(R.id.et_textInput_3);

        changeNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String film = selectedFilms();

                Context context = ThirdActivityFilms.this;
                Class destinationActivity = GetFilmActivity.class;
                Intent GetFilmActivityChange = new Intent(context, destinationActivity);

                GetFilmActivityChange.putExtra(Intent.EXTRA_TEXT, film);
                startActivity(GetFilmActivityChange);
            }
            public String selectedFilms() {
                String[] films = new String[3];
                int position = (int) (Math.random()*3);

                films[0] = selectedFilm_1.getText().toString();
                films[1] = selectedFilm_2.getText().toString();
                films[2] = selectedFilm_3.getText().toString();

                return films[position];
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