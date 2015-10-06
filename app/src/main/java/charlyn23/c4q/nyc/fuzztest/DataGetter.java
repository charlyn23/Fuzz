package charlyn23.c4q.nyc.fuzztest;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by charlynbuchanan on 10/3/15.
 */
public class DataGetter {
    public DataGetter() {
        new AsyncClass().execute();
    }

    static ArrayList<JSONObject> items;
    public static String id;
    public static boolean hasData;



    class AsyncClass extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String jsonURL = "http://quizzes.fuzzstaging.com/quizzes/mobile/1/data.json";
            URL url = null;
            try {
                url = new URL(jsonURL);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                if ((connection != null) || (url != null)) {
                    Log.v("status", "CONNECTED");
                }
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("JSON", "Malformed url: " + e.toString());

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("JSON", "IOException url" + e.toString());

            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            jsonString = s;
            Log.v("JSON", s);
            getItems();


        }
    }

    private static String jsonString = "";

    public static String getJsonString() {
        return jsonString;
    }

    //Get each item of JSON object
    public static ArrayList getItems(){
        String json = getJsonString();
        JSONObject item = null;
        ArrayList<JSONObject> items = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                item = (JSONObject) array.get(i);
                items.add(item);
                id = item.getString("id");
                Log.d("id = " , id); //good
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON", "Malformed url: " + e.toString());
        }
        Log.d("items size :", String.valueOf(items.size()));

        return items;
    }

}
