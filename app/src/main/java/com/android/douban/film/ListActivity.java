package com.android.douban.film;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends Activity {

    private static String TAG = "ListActivity";
    private final OkHttpClient client = new OkHttpClient();
    private String mType;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mTv = findViewById(R.id.content);
        Intent intent = getIntent();
        mType = intent.getStringExtra("type");
        ((TextView) findViewById(R.id.title)).setText(mType);
        getData();
    }

    private void getData () {
        Request request = new Request.Builder()
                .url("https://movie.douban.com/j/search_subjects?type=movie&tag=" + mType + "&sort=recommend&page_limit=20&page_start=0")
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG,"onFailure: " + e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.i(TAG, body);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTv.setText(body);
                    }
                });
            }
        });
    }
}