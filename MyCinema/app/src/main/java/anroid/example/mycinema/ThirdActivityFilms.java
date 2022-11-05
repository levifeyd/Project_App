package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivityFilms extends AppCompatActivity {
    private Button button_back;
    private List<View> allEds;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_acivity_films);

        Button changeNextActivity = (Button) findViewById(R.id.button_add);
        //инициализировали наш массив с edittext
        allEds = new ArrayList<View>();

        //находим наш linear который у нас под кнопкой add edittext в activity_main.xml
        final LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        changeNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные
                final View view = getLayoutInflater().inflate(R.layout.custom_layout, null);
                Button deleteField = (Button) view.findViewById(R.id.button2);
                EditText text = (EditText) view.findViewById(R.id.editText);
                text.setText("Some movie");
                //добавляем все что создаем в массив
                allEds.add(view);
                //добавляем елементы в linearlayout
                linear.addView(view);

                deleteField.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            //получаем родительский view и удаляем его
                            ((LinearLayout) view.getParent()).removeView(view);
                            //удаляем эту же запись из массива что бы не оставалось мертвых записей
                            allEds.remove(view);
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
                //преобразуем наш ArrayList в просто String Array
                String[] items = new String[allEds.size()];
                //запускаем чтение всех елементов этого списка и запись в массив
                for (int i = 0; i < allEds.size(); i++) {
                    items[i] = ((EditText) allEds.get(i).findViewById(R.id.editText)).getText().toString();
                }

                Context context = ThirdActivityFilms.this;
                Class destinationActivity = GetFilmActivity.class;
                Intent SecondActivityChange = new Intent(context, destinationActivity);
                SecondActivityChange.putExtra(Intent.EXTRA_TEXT, selectFilmRandom(items));
                startActivity(SecondActivityChange);
            }
            public String selectFilmRandom(String[] src) {
                int position = (int)(Math.random() * counter);
                return src[position];
            }
        });
        button_back = findViewById(R.id.b_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                Context context = ThirdActivityFilms.this;
                Class destinationActivity = SecondActivity.class;
                Intent SecondActivityChange = new Intent(context, destinationActivity);
                startActivity(SecondActivityChange);
                finish();
            }
        });
    }
}
