package android.example.AppCinema.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.fragment.app.Fragment;

public class DBSingleton extends Fragment{
    private MyDbManager myDbManager;

    public DBSingleton() {
    }
    /**
     * Внутренний класс уровня класса, то есть внутренний класс статического типа-члена, экземпляр внутреннего класса и экземпляр внешнего класса
     * Нет никакого отношения привязки, и он будет загружен только при его вызове, таким образом реализуя отложенную загрузку.
     */
    private static class SingletonHolder{
        /**
         * Статический инициализатор, безопасность потоков гарантируется JVM
         */
        private static DBSingleton instance = new DBSingleton();
    }
    public MyDbManager getDb(Context context) {
        myDbManager = new MyDbManager(context);
        return myDbManager;
    }

    public static DBSingleton getInstance(){
        return SingletonHolder.instance;
    }
}
