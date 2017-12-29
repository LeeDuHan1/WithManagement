package www.withhome360.com.withmanagement;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by lee-2 on 2017-10-29.
 */

public class BackGroundTask extends AsyncTask<String, String, String> {
    private ProgressDialog progressDialog;

    private TextView editText;
    private String pUrl;
    private String result;
    private SingleAdapter adapter;
    private ListView listView;

    public BackGroundTask(ProgressDialog progressDialog, String url, TextView editText, SingleAdapter adapter, ListView listView) {
        super();
        this.progressDialog = progressDialog;
        this.pUrl = url;
        this.editText = editText;
        this.adapter = adapter;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog.show();
    }

    @Override
    protected String doInBackground(String... string) {
        GetParsingData gpd = new GetParsingData(pUrl);
        gpd.start();
        try {
            gpd.join();
            result = gpd.GetResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        try{
            JsonParser jp = new JsonParser(string);
            this.adapter = jp.DoJsonPasing(adapter);
            this.editText.setText(result);
            listView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
//        this.editText.invalidate();
        this.progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public SingleAdapter getAdapter(){
        return adapter;
    }

}
