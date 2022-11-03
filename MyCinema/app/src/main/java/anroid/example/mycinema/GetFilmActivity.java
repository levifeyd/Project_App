package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GetFilmActivity extends AppCompatActivity {
private TextView textView;
private Button back;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_film_activity);

        textView = findViewById(R.id.tv_movie);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String textEntered= intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            textView.setText(textEntered);
        } else {
            textView.setText("You aren`t signed any movie");
        }
        back = findViewById(R.id.b_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = GetFilmActivity.this;
                Class destinationActivity = ThirdActivityFilms.class;
                Intent ThirdActivityChange = new Intent(context, destinationActivity);
                startActivity(ThirdActivityChange);
            }
        });

    }
}
