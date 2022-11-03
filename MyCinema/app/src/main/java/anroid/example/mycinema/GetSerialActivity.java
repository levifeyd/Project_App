package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GetSerialActivity extends AppCompatActivity {
    private TextView textView;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_serial_activity);

        textView = findViewById(R.id.tv_serial);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String textEntered= intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            textView.setText(textEntered);
        }
        back = findViewById(R.id.b_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = GetSerialActivity.this;
                Class destinationActivity = ThirdActivitySerials.class;
                Intent ThirdActivityChange = new Intent(context, destinationActivity);
                startActivity(ThirdActivityChange);
            }
        });
    }

}
