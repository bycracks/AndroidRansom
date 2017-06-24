package bycracks.androidransom;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // Service <-> Activity 사이의 통신
        // 데이터 전달 필요없으면 return null;

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Service 가장 먼저 호출 (최초에 한번만)

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 서비스 호출될 때마다 실행
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 서비스가 종료될 때 실행
    }
}
