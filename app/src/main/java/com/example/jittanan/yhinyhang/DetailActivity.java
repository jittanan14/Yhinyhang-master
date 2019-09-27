package com.example.jittanan.yhinyhang;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jittanan.yhinyhang.Adapter.ViewPagerAdapter;
import com.example.jittanan.yhinyhang.Fragments.HowtoFragment;
import com.example.jittanan.yhinyhang.Fragments.IngredientFragment;

public class DetailActivity extends AppCompatActivity {
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        back = findViewById(R.id.button_back_listmenu);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new IngredientFragment(), "ส่วนผสม");
        viewPagerAdapter.addFragment(new HowtoFragment(), "วิธีทำ");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);





    }
}

