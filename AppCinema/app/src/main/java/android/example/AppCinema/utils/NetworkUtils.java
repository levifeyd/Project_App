package android.example.AppCinema.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.kinopoisk.dev";
    private static final String VK_USERS_GET = "/movie";
    private static final String ACCESS_TOKEN = "token";

    public static URL generateURL() {
        Uri builtUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(ACCESS_TOKEN,
                        "GH51PY4-RTK4HQT-HXWFF8F-290HEDP")
                .appendQueryParameter("field", "year")
                .appendQueryParameter("search", "2017-2020")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromURL(URL url) throws IOException { // получить ответ от ЮРЛ
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();  // кастим до класса HTTP и открываем соединиение
        try {
            InputStream in = urlConnection.getInputStream();  // можем считать данные с соединенияи возваращет объект класса Инпут

            Scanner scanner = new Scanner(in); // пердаме в конструтор наш ин
            scanner.useDelimiter("\\A");  // разделтель строки , что бы не делить троки на подстроки

            boolean hasInput = scanner.hasNext();  //  есть ли какая-то строка

            if (hasInput) {  //  есть какие-то данные
                return scanner.next(); // вернет всю строку , т.к "\\A"
            } else {
                return null;
            }
        } catch (UnknownHostException e) {
            return null;
        } finally { // если будет вызвана исключение, то все равно выйдет дисконнект
            urlConnection.disconnect(); // отключаемся от http
        }
    }
}

