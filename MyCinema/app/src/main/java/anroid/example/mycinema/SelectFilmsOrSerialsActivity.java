package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SelectFilmsOrSerialsActivity extends AppCompatActivity {
    private Button NextActivityFilms;
    private Button NextActivitySerials;
    private Button buttonBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_films_serials_activity);

        NextActivityFilms = findViewById(R.id.b_films);
        NextActivityFilms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SelectFilmsOrSerialsActivity.this;
                Class destinationActivity = FilmsActivity.class;
                Intent ThirdActivityChange = new Intent(context, destinationActivity);
                startActivity(ThirdActivityChange);
             }
        });

        NextActivitySerials = findViewById(R.id.b_serials);
        NextActivitySerials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SelectFilmsOrSerialsActivity.this;
                Class destinationActivity = SerialsActivity.class;
                Intent ActivityChange = new Intent(context, destinationActivity);
                startActivity(ActivityChange);
            }
        });

        buttonBack = findViewById(R.id.b_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SelectFilmsOrSerialsActivity.this;
                Class destinationActivity = MainActivity.class;
                Intent ActivityChange = new Intent(context, destinationActivity);
                startActivity(ActivityChange);
                finish();
            }
        });
    }
}
