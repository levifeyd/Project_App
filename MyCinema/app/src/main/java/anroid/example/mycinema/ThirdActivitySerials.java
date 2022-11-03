package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivitySerials extends AppCompatActivity {
    private Button changeNextActivity;
    private Button changeNextActivityBack;
    private EditText selectedSerial_1;
    private EditText selectedSerial_2;
    private EditText selectedSerial_3;
    private EditText selectedSerial_4;
    private EditText selectedSerial_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity_serilas);

        changeNextActivity = findViewById(R.id.b_save_serial);

        selectedSerial_1 = findViewById(R.id.et_textInput);
        selectedSerial_2 = findViewById(R.id.et_textInput_2);
        selectedSerial_3 = findViewById(R.id.et_textInput_3);
        selectedSerial_4 = findViewById(R.id.et_textInput_4);
        selectedSerial_5 = findViewById(R.id.et_textInput_5);

        changeNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serial = selectedSerial();
                if (serial != null) {
                    Context context = ThirdActivitySerials.this;
                    Class destinationActivity = GetSerialActivity.class;
                    Intent GetSerialActivityChange = new Intent(context, destinationActivity);
                    GetSerialActivityChange.putExtra(Intent.EXTRA_TEXT, serial);
                    startActivity(GetSerialActivityChange);
                } else {
                    Context context = ThirdActivitySerials.this;
                    Toast toast = Toast.makeText (context, "Please enter all position for serials", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            public String selectedSerial() {
                String[] serials = new String[5];
                String result = null;
                int position = (int)(Math.random() * 5);

                serials[0] = selectedSerial_1.getText().toString();
                serials[1] = selectedSerial_2.getText().toString();
                serials[2] = selectedSerial_3.getText().toString();
                serials[3] = selectedSerial_4.getText().toString();
                serials[4] = selectedSerial_5.getText().toString();

                if (notEmpty(serials[0]) && notEmpty(serials[1]) && notEmpty(serials[2])
                        && notEmpty(serials[3]) && notEmpty(serials[4])) {
                    result = serials[position];
                }
                return result;
            }
            public boolean notEmpty(String serial) {
                return !(serial.equals(""));
            }
        });

        changeNextActivityBack = findViewById(R.id.b_back_serial);
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
