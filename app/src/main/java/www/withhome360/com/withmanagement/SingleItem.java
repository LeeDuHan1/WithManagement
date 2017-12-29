package www.withhome360.com.withmanagement;

/**
 * Created by BARto on 2017-12-27.
 */

public class SingleItem {
    int id;
    String title;
    String name;
    String phone;
    String address;
    String addressDetail;


    public SingleItem(Integer id, String title, String name, String phone, String address, String addressDetail){
        this.id = id;
        this.title = title;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.addressDetail = addressDetail;
    }
    public void setID(int id){ this.id = id; }
    public Integer getID() { return id;}
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    public void setAddressDetail(String detail){
        this.addressDetail = detail;
    }
    public String getAddress_detail(){ return addressDetail; }

}
