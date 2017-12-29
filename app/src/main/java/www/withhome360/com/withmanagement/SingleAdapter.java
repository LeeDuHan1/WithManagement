package www.withhome360.com.withmanagement;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by BARto on 2017-12-27.
 */

public class SingleAdapter extends BaseAdapter {
    ArrayList<SingleItem> items = new ArrayList<SingleItem>();
    Context context;

    public SingleAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(SingleItem item){
        items.add(item);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SingleItemView itemView = new SingleItemView(context);
        SingleItem item = items.get(i);
        itemView.setTitle(item.getTitle());
        itemView.setName(item.getName());
        itemView.setPhone(item.getPhone());
        itemView.setAddress(item.getAddress()+" "+item.getAddress_detail());
//        itemView.setAddressDetail(item.getAddress_detail());

        return itemView;
    }

    public void clearItems(){ items.clear(); }
}
