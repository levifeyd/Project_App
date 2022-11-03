package anroid.example.mycinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_start;
    private Button button_exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_start = findViewById(R.id.b_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = SecondActivity.class;
                Intent SecondActivityIntent = new Intent(context, destinationActivity);
                startActivity(SecondActivityIntent);
            }
        });
        button_exit = findViewById(R.id.b_exit);
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = SecondActivity.class;
                Intent SecondActivityIntent = new Intent(context, destinationActivity);
                System.exit(0);
            }
        });

    }
}