package android.example.AppCinema.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(@Nullable Context context) {
        super(context, dataBaseUsers.DB_NAME, null, dataBaseUsers.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dataBaseUsers.TABLE_STRUCTURE_USERS);
        db.execSQL(dataBaseUsers.TABLE_STRUCTURE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dataBaseUsers.DROP_TABLE);
        db.execSQL(dataBaseUsers.DROP_TABLE_MOVIE);
        onCreate(db);
    }
}
