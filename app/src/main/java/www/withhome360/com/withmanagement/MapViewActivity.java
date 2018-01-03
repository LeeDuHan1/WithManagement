package www.withhome360.com.withmanagement;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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

    TextView dateTv, timeTv, nameTv, phoneTv, titleTv, addressTv, room_typeTv, rent_typeTv, room_numberTv, public_sizeTv, private_sizeTv;
    CheckBox anytimeCb, short_termCb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        mapSetting();

        dateTv = findViewById(R.id.date);
        dateTv.setText(date);
        timeTv = findViewById(R.id.time);
        timeTv.setText(time);
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
        room_numberTv.setText(room_number);
        public_sizeTv = findViewById(R.id.public_size);
        public_sizeTv.setText(public_size+"평");
        private_sizeTv = findViewById(R.id.private_size);
        private_sizeTv.setText(private_size+"평");
;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void mapSetting(){
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

    public void onCallBtnClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+this.phone));
        startActivity(intent);
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
