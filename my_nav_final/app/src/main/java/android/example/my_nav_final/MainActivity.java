package android.example.my_nav_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private Button button_next;
    private Button button_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragment1NextClick();
            }
        });
        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
    }
    public void onFragment1NextClick() {
        navController.navigate(R.id.fragment2);
    }

    public void onFragment1BackClick() {}


    public void onFragment2NextClick() {
        navController.navigate(R.id.fragment3);
    }

    public void onFragment2BackClick() {
        navController.popBackStack();
    }


    public void onFragment3NextClick() {}

    public void onFragment3BackClick() {
        navController.popBackStack();
    }
}