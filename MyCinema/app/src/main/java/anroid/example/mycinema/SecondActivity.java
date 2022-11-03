package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
    private Button changeNextActivityFilms;
    private Button changeNextActivitySerials;
    private Button button_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        changeNextActivityFilms = findViewById(R.id.b_films);
        changeNextActivityFilms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SecondActivity.this;
                Class destinationActivity = ThirdActivityFilms.class;
                Intent ThirdActivityChange = new Intent(context, destinationActivity);
                startActivity(ThirdActivityChange);
             }
        });

        changeNextActivitySerials = findViewById(R.id.b_serials);
        changeNextActivitySerials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SecondActivity.this;
                Class destinationActivity = ThirdActivitySerials.class;
                Intent ThirdActivityChange = new Intent(context, destinationActivity);
                startActivity(ThirdActivityChange);
            }
        });

        button_back = findViewById(R.id.b_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SecondActivity.this;
                Class destinationActivity = MainActivity.class;
                Intent MainActivityChange = new Intent(context, destinationActivity);
                startActivity(MainActivityChange);
            }
        });
    }
}
