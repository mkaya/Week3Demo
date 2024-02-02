package com.example.week3live;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IntentReceiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_receiver);
        TextView textView=findViewById(R.id.messageText);
        Intent intent = getIntent();
        if(Intent.ACTION_SEND.equals(intent.getAction()) &&
                "text/plain".equals(intent.getType())){
            String message = intent.getStringExtra(Intent.EXTRA_TEXT).toString();
            textView.setText(message);
        }
    }
}