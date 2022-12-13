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
    // for table USERS_MOVIES
    public void insertToDbUsersMovie(String users_id, String movie_id, int like) {
        ContentValues cv = new ContentValues();
        cv.put(dataBaseUsers._ID_USERS, users_id);
        cv.put(dataBaseUsers._ID_MOVIES, movie_id);
        db.insert(dataBaseUsers.TABLE_NAME_USERS_MOVIES, null, cv);
    }

    public String getFromDbUsersMovie(String userId, String movieId) {
        db.execSQL(dataBaseUsers.TABLE_STRUCTURE_USERS_MOVIES);
        String resultMovieId = null;
        String sqlQuery = "select _id_users, _id_movies" + " from "
                + dataBaseUsers.TABLE_NAME_USERS_MOVIES +
                " where _id_movies = ?  AND _id_users = ?";  // смотрим есть ли у этого юзера уже такой фильм
        Cursor cursor = db.rawQuery(sqlQuery, new String[] {movieId, userId});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                resultMovieId  = cursor.getString(1);
            }
            cursor.close();
        }
        return resultMovieId;
    }

    public void closeDb() {
        db.close();
    }

    public void dropDb() {
        Cursor cursorUsers = db.rawQuery("DROP TABLE " + dataBaseUsers.TABLE_NAME_USERS, new String[] {});
        cursorUsers.close();
        Cursor cursorMovies = db.rawQuery("DROP TABLE " + dataBaseUsers.TABLE_NAME_USERS_MOVIES, new String[] {});
        cursorMovies.close();
    }
}
