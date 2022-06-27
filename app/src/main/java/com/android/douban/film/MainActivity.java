package com.android.douban.film;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
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
        Resources resources = getResources();
        String[] catesArray = resources.getStringArray(R.array.categories);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 160);
        params.setMargins(0, 20, 0 ,20);
        for (int i = 0; i < catesArray.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(catesArray[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tv.setTextColor(resources.getColor(R.color.primary));
            tv.setBackground(resources.getDrawable(R.drawable.cate_item_selector));
            tv.setBackgroundColor(0xFFF5F5F5);
            tv.setGravity(Gravity.CENTER);
            mCatesView.addView(tv, params);
        }
    }
}