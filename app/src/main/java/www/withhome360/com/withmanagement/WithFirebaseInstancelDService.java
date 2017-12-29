package www.withhome360.com.withmanagement;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WithFirebaseInstancelDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyID";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d(TAG,"onTokenRefresh() 호출됨");

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed Token : " +refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        Request request = new Request.Builder()
                .url("http://www.withhome360.com/fcm/token_register.php")
                .post(body)
                .build();
        try{
            client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
