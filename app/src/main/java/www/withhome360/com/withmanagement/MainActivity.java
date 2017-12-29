package www.withhome360.com.withmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

public class MainActivity extends AppCompatActivity {
    GetParsingData gpd;
    private TextView mTextMessage;
    public ProgressDialog dialog;
    private BackGroundTask backGroundTask;
    private String enrollUrl = "http://www.withhome360.com/bringEnrollDb.php";
    private String completed_enrollUrl = "http://www.withhome360.com/bringCompletedEnrollDb.php";
    String jsonString;
    SingleAdapter adapter;
    ListView listView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    adapter.clearItems();
                    adapter.notifyDataSetChanged();
                    try {
                        BackGroundTask backGroundTask = new BackGroundTask(dialog, enrollUrl,  mTextMessage, adapter, listView);
                        backGroundTask.execute().get();
                    }catch (Exception e){
                       e.printStackTrace();
                    }
                    return true;
                case R.id.navigation_notifications:
                    adapter.clearItems();
                    adapter.notifyDataSetChanged();
                    try {
                        BackGroundTask backGroundTask = new BackGroundTask(dialog, completed_enrollUrl,  mTextMessage, adapter, listView);
                        backGroundTask.execute().get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("촬영신청목록을 불러오고 있습니다.");
//        backGroundTask = new BackGroundTask(dialog,baseUrl,mTextMessage);

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();
        listView = findViewById(R.id.listView);
        adapter = new SingleAdapter(getApplicationContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                SingleItem item = (SingleItem) adapter.getItem(i);
//                Toast.makeText(getApplicationContext(),"선택 : "+item.getID(),Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingleItem item = (SingleItem) adapter.getItem(i);
                Toast.makeText(getApplicationContext(),"선택 : "+item.getID(),Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

//    private void getAppKeyHash() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for (android.content.pm.Signature signature : info.signatures) {
//                MessageDigest md;
//                md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String something = new String(Base64.encode(md.digest(), 0));
//                Log.d("Hash key", something);
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            Log.e("name not found", e.toString());
//        }
//    }

}
