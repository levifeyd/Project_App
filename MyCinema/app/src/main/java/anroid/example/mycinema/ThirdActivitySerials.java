package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivitySerials extends AppCompatActivity {
    private Button button_back;
    private List<View> allEds;
    private ArrayList<View> allSerials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity_serilas);

        Button changeNextActivity = (Button) findViewById(R.id.button_add);
        //инициализировали наш массив с edittext
        allSerials = new ArrayList<View>();

        //находим наш linear который у нас под кнопкой add edittext в activity_main.xml
        final LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        changeNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные
                final View view = getLayoutInflater().inflate(R.layout.custom_layout, null);
                Button deleteField = (Button) view.findViewById(R.id.button_delete);
                EditText text = (EditText) view.findViewById(R.id.editText);
                text.setText("Some serial");
                //добавляем все что создаем в массив
                allSerials.add(view);
                //добавляем елементы в linearlayout
                linear.addView(view);


                deleteField.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            //получаем родительский view и удаляем его
                            ((LinearLayout) view.getParent()).removeView(view);
                            //удаляем эту же запись из массива что бы не оставалось мертвых записей
                            allSerials.remove(view);
                        } catch(IndexOutOfBoundsException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        Button showDataBtn = (Button) findViewById(R.id.button_random);
        showDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int)(Math.random() * allSerials.size());
                String result = ((EditText) allSerials.get(position).findViewById(R.id.editText)).getText().toString();

                Context context = ThirdActivitySerials.this;
                Class destinationActivity = GetSerialActivity.class;
                Intent SecondActivityChange = new Intent(context, destinationActivity);
                SecondActivityChange.putExtra(Intent.EXTRA_TEXT, result);
                startActivity(SecondActivityChange);
            }
        });
        button_back = findViewById(R.id.b_back_button);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = ThirdActivitySerials.this;
                Class destinationActivity = SecondActivity.class;
                Intent SecondActivityChange = new Intent(context, destinationActivity);
                startActivity(SecondActivityChange);
                finish();
            }
        });
    }
}
