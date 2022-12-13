package android.example.AppCinema.dataBase;

public class dataBaseUsers {
    public static final String TABLE_NAME_USERS = "Users";   // table USERS
    public static final String _ID = "_id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE_USERS = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME_USERS + " (" + _ID + " INTEGER PRIMARY KEY," + EMAIL + " TEXT," +
            PASSWORD + " TEXT)";
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME_USERS;

    public static final String TABLE_NAME_MOVIE = "Movies";  // table Movies
    public static final String MOVIE = "movie";
    public static final String _ID_MOVIE = "id_movie";
    public static final String TABLE_STRUCTURE_MOVIE = "CREATE TABLE IF NOT EXISTS " +
                        TABLE_NAME_MOVIE + " (" + _ID_MOVIE + " INTEGER PRIMARY KEY," +
                        MOVIE + " TEXT)";
    public static final String DROP_TABLE_MOVIE = " DROP TABLE IF EXISTS " + TABLE_NAME_MOVIE;

    public static final String TABLE_NAME_USERS_MOVIES = "Users_movies";   // table USERS_MOVIES
    public static final String _ID_USERS = "_id_users";
    public static final String _ID_MOVIES = "_id_movies";
    public static final String TABLE_STRUCTURE_USERS_MOVIES = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME_USERS_MOVIES + " (" + _ID_USERS+ " INTEGER PRIMARY KEY," +
            _ID_MOVIES + " INTEGER PRIMARY KEY)";
    public static final String DROP_TABLE_USERS_MOVIE = " DROP TABLE IF EXISTS " + TABLE_NAME_USERS_MOVIES;
}

