package android.example.AppCinema.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class MyDbManager {
    private final Context context;
    private final MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public void openDb() {
        db = myDbHelper.getWritableDatabase();
    }

    public void insertToDbUsers(String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put(dataBaseUsers.EMAIL, email);
        cv.put(dataBaseUsers.PASSWORD, password);
        db.insert(dataBaseUsers.TABLE_NAME_USERS, null, cv);
    }

    public void insertToDbMovie(String user, String movie, String id) {
        ContentValues cv = new ContentValues();
        cv.put(dataBaseUsers._ID_MOVIE, id);
        cv.put(dataBaseUsers.MOVIE, movie);
        db.insert(dataBaseUsers.TABLE_NAME_MOVIE, null, cv);
    }

    public String getFromDbMovieId(String id) {
        db.execSQL(dataBaseUsers.TABLE_NAME_MOVIE);
        String sqlQuery = "select movie" + "from " + dataBaseUsers.TABLE_NAME_MOVIE +
                " where _id = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[] {id});
        return id;
    }

    public String getFromDb(String email) {
        db.execSQL(dataBaseUsers.TABLE_STRUCTURE_USERS);
        String password = null;
        String sqlQuery = "select email, password " + "from " + dataBaseUsers.TABLE_NAME_USERS +
                " where email = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[] {email});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                password = cursor.getString(1);
            }
            cursor.close();
        }
        return password;
    }

    public void closeDb(){
        db.close();
    }

    public void dropDb(){
        Cursor cursorUsers = db.rawQuery("DROP TABLE " + dataBaseUsers.TABLE_NAME_USERS, new String[] {});
        cursorUsers.close();
        Cursor cursorMovies = db.rawQuery("DROP TABLE " + dataBaseUsers.TABLE_NAME_MOVIE, new String[] {});
        cursorMovies.close();
    }
}
