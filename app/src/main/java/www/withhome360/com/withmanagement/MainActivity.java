package www.withhome360.com.withmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
//    private TextView mTextMessage;
    public ProgressDialog dialog;
    private BackGroundTask backGroundTask;
    private String enrollUrl = "http://www.withhome360.com/bring/bringEnrollDb.php";
    private String completed_enrollUrl = "http://www.withhome360.com/bring/bringCompletedEnrollDb.php";
    String jsonString;
    SingleAdapter adapter,adapter2;
    ListView listView,listView2;
    Boolean state = true;
    int naviState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        getAppKeyHash();

//        mTextMessage = (TextView) findViewById(R.id.message);
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
                SingleItem item = (SingleItem) adapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(),MapViewActivity.class);
                intent.putExtra("state",state);
                intent.putExtra("id",item.getID());
                intent.putExtra("date",item.getDate());
                intent.putExtra("time",item.getTime());
                intent.putExtra("anytime",item.getAnytime());
                intent.putExtra("name",item.getName());
                intent.putExtra("phone",item.getPhone());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("address",item.getAddress()+" "+ item.getAddress_detail());
                intent.putExtra("lat",item.getLat());
                intent.putExtra("lng",item.getLng());
                intent.putExtra("room_type",item.getRoom_type());
                intent.putExtra("rent_type",item.getRent_type());
                intent.putExtra("short_term",item.getShort_term());
                intent.putExtra("room_number",item.getRoom_number());
                intent.putExtra("public_size",item.getPublic_size());
                intent.putExtra("private_size",item.getPrivate_size());

                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingleItem item = (SingleItem) adapter.getItem(i);
                Toast.makeText(getApplicationContext(),"id : "+item.getID()+"상관 : "+item.getAnytime(),Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"resume"+naviState,Toast.LENGTH_SHORT);
        if(naviState==2){
            adapter.clearItems();
            adapter.notifyDataSetChanged();
            try {
                BackGroundTask backGroundTask = new BackGroundTask(dialog, enrollUrl, adapter, listView);
                backGroundTask.execute().get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(naviState==3){
            adapter.clearItems();
            adapter.notifyDataSetChanged();
            try {
                BackGroundTask backGroundTask = new BackGroundTask(dialog, completed_enrollUrl, adapter, listView);
                backGroundTask.execute().get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    naviState = 1;
                    adapter.clearItems();
                    adapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_takephoto:
                    naviState = 2;
                    adapter.clearItems();
                    adapter.notifyDataSetChanged();
                    try {
                        BackGroundTask backGroundTask = new BackGroundTask(dialog, enrollUrl, adapter, listView);
                        backGroundTask.execute().get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    state = true;
                    return true;
                case R.id.navigation_photoclear:
                    naviState = 3;
                    adapter.clearItems();
                    adapter.notifyDataSetChanged();
                    try {
                        BackGroundTask backGroundTask = new BackGroundTask(dialog, completed_enrollUrl, adapter, listView);
                        backGroundTask.execute().get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    state = false;
                    return true;
            }
            return false;
        }
    };
//  private void getAppKeyHash() {
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
