package www.withhome360.com.withmanagement;

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
        String tiltle;
        String name;
        String phone;
        String address;
        String address_detail;
        int id;

        try{
            JSONArray jarray = new JSONArray(jsonString);
            for(int i = 0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);
                tiltle = jObject.getString("title");
                name = jObject.getString("name");
                phone = jObject.getString("phone");
                address = jObject.getString("address");
                address_detail = jObject.getString("address_detail");
                id = Integer.parseInt(jObject.getString("id"));
                adapter.addItem(new SingleItem(id,tiltle,name,phone,address,address_detail));
//                adapter.notifyDataSetChanged();

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return adapter;
    }
}
