package com.android.douban.film;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mCatesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCatesView = findViewById(R.id.categories);
        initCategories();
    }

    private void initCategories () {
        String[] catesArray = getResources().getStringArray(R.array.categories);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 160);
        params.setMargins(0, 20, 0 ,20);
        for (int i = 0; i < catesArray.length; i++) {
            View view = createItem(catesArray[i]);
            mCatesView.addView(view, params);
        }
    }

    private View createItem (String value) {
        TextView tv = new TextView(this);
        tv.setText(value);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv.setTextColor(getResources().getColor(R.color.primary));
        tv.setBackgroundColor(0xFFF5F5F5);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("type", value);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.setClass(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        return tv;
    }

}