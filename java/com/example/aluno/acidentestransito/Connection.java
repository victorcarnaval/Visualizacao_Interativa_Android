package com.example.aluno.acidentestransito;
import android.os.StrictMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aluno on 13/03/2017.
 */
public class Connection {
    public List<Marker> getData() throws JSONException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL("https://api.myjson.com/bins/5c32y");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                result.append(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Buscando o JSON" + result);
        List<Marker> finalResult = generateJSON(new JSONArray(result.toString()));

        return finalResult;
    }

    public List<Marker> generateJSON(JSONArray json) {

        List<Marker> found = new LinkedList<Marker>();

        try {

            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                found.add(new Marker(obj.getString("end"), obj.getInt("total_acidentes"), obj.getInt("acidentes_fatais"), obj.getDouble("lat"), obj.getDouble("long")));

            }

        } catch (JSONException e) {
            // handle exception
        }

        return found;
    }
}