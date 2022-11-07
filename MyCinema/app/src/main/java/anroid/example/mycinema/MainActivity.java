package anroid.example.mycinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonStart;
    private Button buttonExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.b_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = SelectFilmsOrSerialsActivity.class;
                Intent SecondActivityIntent = new Intent(context, destinationActivity);
                startActivity(SecondActivityIntent);
            }
        });

        buttonExit = findViewById(R.id.b_exit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = SelectFilmsOrSerialsActivity.class;
                Intent SecondActivityIntent = new Intent(context, destinationActivity);
                System.exit(0);
            }
        });
    }
}