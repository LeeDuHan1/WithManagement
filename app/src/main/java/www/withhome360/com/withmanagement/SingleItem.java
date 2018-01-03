package www.withhome360.com.withmanagement;

/**
 * Created by BARto on 2017-12-27.
 */

public class SingleItem {
    private int id;
    private String date, time;
    private boolean anytime;
    private String name, phone, title, address, addressDetail;
    private double lat, lng;
    private String room_type, rent_type;
    private boolean short_term;
    private String room_number;
    private double public_size,private_size;

    public SingleItem(int id, String name, String phone,  String title, String address, String addressDetail, double lat, double lng){
        this.id = id;
        this.title = title;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.addressDetail = addressDetail;
        this.lat = lat;
        this.lng = lng;
    }
    public SingleItem(int id, String date, String time, boolean anytime,  String name, String phone, String title, String address, String addressDetail, double lat, double lng, String room_type, String rent_type, boolean  short_term, String room_number, double public_size, double private_size){
        this.id = id;
        this.date = date;
        this.time = time;
        this.anytime = anytime;
        this.name = name;
        this.phone = phone;
        this.title = title;
        this.address = address;
        this.addressDetail = addressDetail;
        this.lat = lat;
        this.lng = lng;
        this.room_type = room_type;
        this.rent_type = rent_type;
        this.short_term = short_term;
        this.room_number = room_number;
        this.public_size = public_size;
        this.private_size = private_size;
    }
    public void setID(int id){ this.id = id; }
    public Integer getID() { return id;}

    public void setDate(String date){ this.date = date; }
    public String getDate() { return date;}

    public void setTime(String time){ this.time = time; }
    public String getTime() { return time;}

    public void setAnytime(boolean anytime){ this.anytime = anytime; }
    public boolean getAnytime() { return anytime;}

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

    public void setAddress_detail(String detail){
        this.addressDetail = detail;
    }
    public String getAddress_detail(){ return addressDetail; }

    public void setLat(double lat){ this.lat = lat; }
    public double getLat() { return lat;}

    public void setLng(double lng){ this.lng = lng; }
    public double getLng() { return lng;}

    public void setRoom_type(String room_type){ this.room_type = room_type; }
    public String getRoom_type() { return room_type;}

    public void setRent_type(String rent_type){ this.rent_type = rent_type; }
    public String getRent_type() { return rent_type;}

    public void setShort_term(boolean short_term){ this.short_term = short_term; }
    public boolean getShort_term() { return short_term;}

    public void setRoom_number(String room_number){ this.room_number = room_number; }
    public String getRoom_number() { return room_number;}

    public void setPublic_size(double public_size){ this.public_size = public_size; }
    public double getPublic_size() { return public_size;}

    public void setPrivate_size(double private_size){ this.private_size = private_size; }
    public double getPrivate_size() { return private_size;}
}
