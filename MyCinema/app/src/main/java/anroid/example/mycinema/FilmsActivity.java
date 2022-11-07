//package anroid.example.mycinema;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import java.util.ArrayList;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class FilmsActivity extends AppCompatActivity {
//    private Button buttonBack;
//    private ArrayList<View> allMovies;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.films_activity);
//
//        Button changeNextActivity = (Button) findViewById(R.id.button_add);
//        //инициализировали наш массив с edittext
//        allMovies = new ArrayList<View>();
//
//        //находим наш linear который у нас под кнопкой add edittext в activity_main.xml
//        final LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
//        changeNextActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные
//
//                final View view = getLayoutInflater().inflate(R.layout.custom_layout, null);
//                Button deleteField = (Button) view.findViewById(R.id.button_delete);
//                EditText text = (EditText) view.findViewById(R.id.editText);
//                text.setText("Some movie");
//                //добавляем все что создаем в массив
//                allMovies.add(view);
//                //добавляем елементы в linearlayout
//                linear.addView(view);
//
//                deleteField.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            //получаем родительский view и удаляем его
//                            ((LinearLayout) view.getParent()).removeView(view);
//                            //удаляем эту же запись из массива что бы не оставалось мертвых записей
//                            allMovies.remove(view);
//                        } catch(IndexOutOfBoundsException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                });
//            }
//            public void initLinear(View view){
//                final View view_2 = getLayoutInflater().inflate(R.layout.custom_layout, null);
//                Button deleteField = (Button) view.findViewById(R.id.button_delete);
//                EditText text = (EditText) view.findViewById(R.id.editText);
//            }
//        });
//
//        Button showDataBtn = (Button) findViewById(R.id.button_random);
//        showDataBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = (int)(Math.random() * allMovies.size());
//                String result = ((EditText) allMovies.get(position).findViewById(R.id.editText)).getText().toString();
//
//                Context context = FilmsActivity.this;
//                Class destinationActivity = GetFilmActivity.class;
//                Intent SecondActivityChange = new Intent(context, destinationActivity);
//                SecondActivityChange.putExtra(Intent.EXTRA_TEXT, result);
//                startActivity(SecondActivityChange);
//            }
//        });
//        buttonBack = findViewById(R.id.b_back_button);
//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//             public void onClick(View view) {
//                Context context = FilmsActivity.this;
//                Class destinationActivity = SelectFilmsOrSerialsActivity.class;
//                Intent SecondActivityChange = new Intent(context, destinationActivity);
//                startActivity(SecondActivityChange);
//                finish();
//            }
//        });
//    }
//}

package anroid.example.mycinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

    public class FilmsActivity extends AppCompatActivity {
        private Button buttonBack;
        private ArrayList<View> allMovies;
        public View view;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.films_activity);

            Button changeNextActivity = (Button) findViewById(R.id.button_add);
            //инициализировали наш массив с edittext
            allMovies = new ArrayList<View>();

            //находим наш linear который у нас под кнопкой add edittext в activity_main.xml
            final LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
            changeNextActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные

                    final View view = getLayoutInflater().inflate(R.layout.custom_layout, null);
                    Button deleteField = (Button) view.findViewById(R.id.button_delete);
                    EditText text = (EditText) view.findViewById(R.id.editText);
                    text.setText("Some movie");
                    //добавляем все что создаем в массив
                    allMovies.add(view);
                    //добавляем елементы в linearlayout
                    linear.addView(view);

                    deleteField.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                //получаем родительский view и удаляем его
                                ((LinearLayout) view.getParent()).removeView(view);
                                //удаляем эту же запись из массива что бы не оставалось мертвых записей
                                allMovies.remove(view);
                            } catch(IndexOutOfBoundsException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
                public void initLinear(View view){
                    final View view_2 = getLayoutInflater().inflate(R.layout.custom_layout, null);
                    Button deleteField = (Button) view.findViewById(R.id.button_delete);
                    EditText text = (EditText) view.findViewById(R.id.editText);
                }
            });

            Button showDataBtn = (Button) findViewById(R.id.button_random);
            showDataBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int)(Math.random() * allMovies.size());
                    String result = ((EditText) allMovies.get(position).findViewById(R.id.editText)).getText().toString();

                    Context context = anroid.example.mycinema.FilmsActivity.this;
                    Class destinationActivity = GetFilmActivity.class;
                    Intent SecondActivityChange = new Intent(context, destinationActivity);
                    SecondActivityChange.putExtra(Intent.EXTRA_TEXT, result);
                    startActivity(SecondActivityChange);
                }
            });
            buttonBack = findViewById(R.id.b_back_button);
            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = anroid.example.mycinema.FilmsActivity.this;
                    Class destinationActivity = SelectFilmsOrSerialsActivity.class;
                    Intent SecondActivityChange = new Intent(context, destinationActivity);
                    startActivity(SecondActivityChange);
                    finish();
                }
            });
        }
    }