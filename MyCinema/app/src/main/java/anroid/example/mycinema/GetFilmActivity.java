package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GetFilmActivity extends AppCompatActivity {
    private TextView recommendedFilm;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_film_activity);

        recommendedFilm = findViewById(R.id.tv_movie);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String textEntered= intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            recommendedFilm.setText(textEntered);
        }
        backButton = findViewById(R.id.b_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = GetFilmActivity.this;
                Class destinationActivity = FilmsActivity.class;
                Intent ThirdActivityChange = new Intent(context, destinationActivity);
                startActivity(ThirdActivityChange);
            }
        });
    }
}
