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
    public void insertToDb(String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put(dataBase.EMAIL, email);
        cv.put(dataBase.PASSWORD, password);
        db.insert(dataBase.TABLE_NAME, null, cv);
    }

    public String getFromDb(String email) {
        db.execSQL(dataBase.TABLE_STRUCTURE);
        String password = null;
        String sqlQuery = "select email, password " + "from " + dataBase.TABLE_NAME +
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
        Cursor c = db.rawQuery("DROP TABLE " + dataBase.TABLE_NAME, new String[] {});
        c.close();
    }
}
