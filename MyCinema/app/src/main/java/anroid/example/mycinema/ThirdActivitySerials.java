package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivitySerials extends AppCompatActivity {
    private Button changeNextActivity;
    private Button changeNextActivityBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity_serilas);

        changeNextActivityBack = findViewById(R.id.b_back);
        changeNextActivityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = ThirdActivitySerials.this;
                Class destinationActivity = SecondActivity.class;
                Intent SecondActivityChange = new Intent(context, destinationActivity);
                startActivity(SecondActivityChange);
            }
        });
    }
}
