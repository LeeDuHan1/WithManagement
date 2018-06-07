package www.withhome360.com.withmanagement;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int id;
    String date, time;
    boolean anytime;
    String name, phone, title, address;
    double lat, lng;
    String room_type, rent_type;
    boolean short_term;
    String room_number;
    double public_size, private_size;
    Fragment mapFragment;
    TextView dateTv, timeTv, nameTv, phoneTv, titleTv, addressTv, room_typeTv, rent_typeTv, room_numberTv, public_sizeTv, private_sizeTv;
    CheckBox anytimeCb, short_termCb;
    Button moveBtn;
    String enrollUrl ="http://www.withhome360.com/management/enrollToCompleted.php";
    String completedUrl ="http://www.withhome360.com/management/completedToEnroll.php";
    Boolean state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        mapSetting();

        moveBtn = findViewById(R.id.moveBtn);
        dateTv = findViewById(R.id.date);
        dateTv.setText("날짜 : "+date);
        timeTv = findViewById(R.id.time);
        timeTv.setText("시간 : "+time);
        anytimeCb = findViewById(R.id.anytime);
        anytimeCb.setChecked(anytime);
        short_termCb = findViewById(R.id.short_term);
        short_termCb.setChecked(short_term);
        nameTv = findViewById(R.id.name);
        nameTv.setText(name);
        phoneTv = findViewById(R.id.phone);
        phoneTv.setText(phone);
        titleTv = findViewById(R.id.title);
        titleTv.setText(title);
        addressTv = findViewById(R.id.address);
        addressTv.setText(address);
//        room_typeTv = findViewById(R.id.room_type);
        rent_typeTv = findViewById(R.id.rent_type);
        rent_typeTv.setText(rent_type.replace(",","\n"));
        room_numberTv = findViewById(R.id.room_number);
        room_numberTv.setText("빈방호실 : "+room_number);
        public_sizeTv = findViewById(R.id.public_size);
        public_sizeTv.setText(public_size+"평");
        private_sizeTv = findViewById(R.id.private_size);
        private_sizeTv.setText(private_size+"평");

        moveBtnSetting();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void mapSetting(){
        this.state = getIntent().getExtras().getBoolean("state");
        this.id = getIntent().getExtras().getInt("id");
        this.date = getIntent().getExtras().getString("date");
        this.time = getIntent().getExtras().getString("time");
        this.anytime = getIntent().getExtras().getBoolean("anytime");
        this.name = getIntent().getExtras().getString("name");
        this.phone = getIntent().getExtras().getString("phone");
        this.title = getIntent().getExtras().getString("title");
        this.address = getIntent().getExtras().getString("address");
        this.lat = getIntent().getExtras().getDouble("lat");
        this.lng = getIntent().getExtras().getDouble("lng");
        this.room_type = getIntent().getExtras().getString("room_type");
        this.rent_type = getIntent().getExtras().getString("rent_type");
        this.short_term = getIntent().getExtras().getBoolean("short_term");
        this.room_number = getIntent().getExtras().getString("room_number");
        this.public_size = getIntent().getExtras().getDouble("public_size");
        this.private_size = getIntent().getExtras().getDouble("private_size");
    }

    public void moveBtnSetting() {
        if (state == false) {
            moveBtn.setText("복구");
            moveBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    GetParsingData gpd = new GetParsingData(completedUrl+"?id="+id);
                    gpd.start();
                    Toast.makeText(getApplicationContext(), "복구", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }else {
            moveBtn.setText("촬영완료");
            moveBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    GetParsingData gpd = new GetParsingData(enrollUrl+"?id="+id);
                    gpd.start();
                    Toast.makeText(getApplicationContext(), "완료", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    public void onCallBtnClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        startActivity(intent);
    }


    public void onCatcherBtnClicked(View view){
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        ScaleMapFragment scaleMapFragment = new ScaleMapFragment();
//        fragmentTransaction.add(R.id.fragment_container,fragment);

/*        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mapFragment = getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getView().setLayoutParams(params);*/

        /*ViewGroup.LayoutParams vl = mapFragment.getView().getLayoutParams();
        vl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        vl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mapFragment.getView().setLayoutParams(vl);*/
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
