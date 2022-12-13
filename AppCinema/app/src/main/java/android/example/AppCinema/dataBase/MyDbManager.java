package android.example.AppCinema.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;


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
    // for table USERS
    public void insertToDbUsers(String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put(dataBaseUsers.EMAIL, email);
        cv.put(dataBaseUsers.PASSWORD, password);
        db.insert(dataBaseUsers.TABLE_NAME_USERS, null, cv);
    }

    public String getFromDbUsers(String email) {
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
    // for table MOVIES
    public void insertToDbMovie(String user, String movie, String id) {
        ContentValues cv = new ContentValues();
        cv.put(dataBaseUsers._ID_MOVIE, id);
        cv.put(dataBaseUsers.MOVIE, movie);
        db.insert(dataBaseUsers.TABLE_NAME_MOVIE, null, cv);
    }

    public String getFromDbMovie(String id) {
        db.execSQL(dataBaseUsers.TABLE_STRUCTURE_MOVIE);
        String sqlQuery = "select movie" + "from " + dataBaseUsers.TABLE_NAME_MOVIE +
                " where _id = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[] {id});
        return id;
    }
    // for table USERS_MOVIES
    public void insertToDbUsersMovie(String users_id, String movie_id) {
        ContentValues cv = new ContentValues();
        cv.put(dataBaseUsers._ID_USERS, users_id);
        cv.put(dataBaseUsers._ID_MOVIE, movie_id);
        db.insert(dataBaseUsers.TABLE_NAME_USERS_MOVIES, null, cv);
    }

    public String getFromDbUsersMovie(String id) {
        db.execSQL(dataBaseUsers.TABLE_STRUCTURE_MOVIE);
        String sqlQuery = "select _id_users" + "from "
                + dataBaseUsers.TABLE_NAME_MOVIE +
                " where _id_movies = ?";  // смотрим есть ли у этого юзера уже такой фильм
        Cursor cursor = db.rawQuery(sqlQuery, new String[] {id});
        return id;
    }

    public void closeDb() {
        db.close();
    }

    public void dropDb() {
        Cursor cursorUsers = db.rawQuery("DROP TABLE " + dataBaseUsers.TABLE_NAME_USERS, new String[] {});
        cursorUsers.close();
        Cursor cursorMovies = db.rawQuery("DROP TABLE " + dataBaseUsers.TABLE_NAME_MOVIE, new String[] {});
        cursorMovies.close();
    }
}
