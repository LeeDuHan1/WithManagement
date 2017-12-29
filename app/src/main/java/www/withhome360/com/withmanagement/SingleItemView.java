package www.withhome360.com.withmanagement;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.jar.Attributes;

/**
 * Created by BARto on 2017-12-27.
 */

public class SingleItemView extends LinearLayout {
    TextView title;
    TextView name;
    TextView phone;
    TextView address;
//    TextView addressDetail;

    public SingleItemView(Context context){
        super(context);
        init(context);
    }

    public SingleItemView(Context context, AttributeSet attr){
        super(context);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.single_item, this, true);

        title = findViewById(R.id.title);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
//        addressDetail = findViewById(R.id.addressDetail);
    }

    public void setTitle(String title){
        this.title.setText(title);
    }
    public void setName(String name){
        this.name.setText(name);
    }
    public void setPhone(String phone) {
        this.phone.setText(phone);
    }
    public void setAddress(String address){
        this.address.setText(address);
    }
//    public void setAddressDetail(String detail){
//        this.addressDetail.setText(detail);
//    }
}
