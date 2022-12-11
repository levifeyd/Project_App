package android.example.AppCinema.dataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class dataBase {
    public static final String TABLE_NAME = "Users";
    public static final String _ID = "_id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + EMAIL + " TEXT," +
            PASSWORD + " TEXT)";
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;
}

