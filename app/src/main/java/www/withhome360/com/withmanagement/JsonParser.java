package www.withhome360.com.withmanagement;

import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 * Created by lee-2 on 2017-10-10.
 */

public class JsonParser {
    private String jsonString, key;
    private String value;

    public JsonParser(String str){
        jsonString = str;
    }

    public String DoJsonPasing(String keyValue)throws IOException{
        this.key = keyValue;

        try{
//            JSONObject jsonObject = new JSONObject(jsonString);
//            value = jsonObject.get(key).toString();
//            Log.d("제이슨",jsonString);
            JSONArray jarray = new JSONArray(jsonString);
            for(int i = 0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);
                value = jObject.getString(key);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return value;
    }

    public SingleAdapter DoJsonPasing(SingleAdapter adapter)throws IOException{
        int id;
        String date, time;
        boolean anytime;
        String title, name, phone, address, address_detail;
        double lat, lng;
        String room_type, rent_type;
        boolean short_term;
        String room_number;
        double public_size,private_size;

        try{
            JSONArray jarray = new JSONArray(jsonString);
            for(int i = 0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);
                id = Integer.parseInt(jObject.getString("id"));
                date = jObject.getString("date");
                time = jObject.getString("time");
                anytime = jObject.getString("anytime").equals("1");
                title = jObject.getString("title");
                name = jObject.getString("name");
                phone = jObject.getString("phone");
                address = jObject.getString("address");
                address_detail = jObject.getString("address_detail");
                lat = Double.parseDouble(jObject.getString("lat"));
                lng = Double.parseDouble(jObject.getString("lng"));
                room_type = jObject.getString("room_type");
                rent_type= jObject.getString("rent_type");
                short_term = jObject.getString("short_term").equals("1");
                room_number = jObject.getString("room_number");
                public_size = Double.parseDouble(jObject.getString("public_size"));
                private_size = Double.parseDouble(jObject.getString("private_size"));

                adapter.addItem(new SingleItem(id,date,time,anytime,name,phone,title,address,address_detail,lat,lng,room_type,rent_type,short_term,room_number,public_size,private_size));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return adapter;
    }
}
