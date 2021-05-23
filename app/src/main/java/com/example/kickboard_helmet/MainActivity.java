package com.example.kickboard_helmet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button btn_scan;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_scan=findViewById(R.id.btn_scan);

        qrScan = new IntentIntegrator(this);

        btn_scan.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //qr 코드 읽는동안
                qrScan.setPrompt("Scanning...");
                qrScan.initiateScan();
                //Intent intent = new Intent(MainActivity.this, QRReader.class);
                //startActivity(intent);
            }
        });
    }

    //qr 결과값 읽기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, requestCode, data);
        if(result != null){
        if(result.getContents() == null) {
            //qr 읽어들인 게 없을때
            Toast.makeText(MainActivity.this, "취소!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
            try{
                //스캔 결과 obj에 저장함
                JSONObject obj = new JSONObject(result.getContents());
                //헬멧 번호 다른 변수에 저장->만들어야함
                //textViewName.setText(obj.getString("helmet"));

            } catch(JSONException e){
                e.printStackTrace();
            }
        }}else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}