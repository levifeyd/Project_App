package android.example.AppCinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.example.AppCinema.R;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        Button button_next = findViewById(R.id.b_start);
        button_next.setOnClickListener(view -> navController.navigate(R.id.fragment2));
    }
}