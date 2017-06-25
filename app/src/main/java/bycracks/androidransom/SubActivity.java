package bycracks.androidransom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button btn_start = (Button) findViewById(R.id.startBtn);
        btn_start.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(SubActivity.this, MyService.class));
            }
        });

        Button btn_end = (Button) findViewById(R.id.stopBtn);
        btn_end.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(SubActivity.this, MyService.class));
            }
        });

    }


}
