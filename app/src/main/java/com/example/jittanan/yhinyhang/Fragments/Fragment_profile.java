package com.example.jittanan.yhinyhang.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jittanan.yhinyhang.Question;
import com.example.jittanan.yhinyhang.R;
import com.example.jittanan.yhinyhang.api.RetrofitClient;
import com.example.jittanan.yhinyhang.models.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_profile extends Fragment {

    private CircleImageView userProfile;
    private TextView emailTextView, usernameTextView, genderTextView, birthdayTextView, elementTextView, foodLoseTextView, bodyTextView, numYhinTextView, numYhangTextView;
    private SharedPreferences sp;
    private String PREF_NAME = "Log in";
    private LinearLayout layout_Gotoquestion;
    private SwipeRefreshLayout swipeRefreshLayout;
    RetrofitClient retro ;

    public Fragment_profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        retro = new RetrofitClient();

        userProfile = view.findViewById(R.id.user_profile);
        layout_Gotoquestion = view.findViewById(R.id.layout_Gotoquestion);
        sp = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        emailTextView = view.findViewById(R.id.email_text_view);
        usernameTextView = view.findViewById(R.id.username_text_view);
        genderTextView = view.findViewById(R.id.gender_text_view);
        birthdayTextView = view.findViewById(R.id.birth_text_view);
        elementTextView = view.findViewById(R.id.element_text_view);
        foodLoseTextView = view.findViewById(R.id.foodlose_text_view);
        bodyTextView = view.findViewById(R.id.body_text_view);
        numYhinTextView = view.findViewById(R.id.num_yhin_text_view);
        numYhangTextView = view.findViewById(R.id.num_yhang_text_view);
        userProfile = view.findViewById(R.id.user_profile);

        //Pull to Refresh
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);



        final String email    = sp.getString("email", "");
        String username = sp.getString("username", "");
        String gender   = sp.getString("gender", "");
        String birthday = sp.getString("birthday", "");
        String element  = sp.getString("element", "");
        String foodLose = sp.getString("foodLose", "");
        String image    = sp.getString("image", "");
        String body     = sp.getString("body", "");
        String numYhin  = sp.getString("numYhin", "");
        String numYhang = sp.getString("numYhang", "");


        emailTextView.setText(email);
        usernameTextView.setText(username);
        birthdayTextView.setText(birthday);
        elementTextView.setText(element);
        if(foodLose.equals("-")){
            foodLoseTextView.setText("ไม่แพ้วัตถุดิบอาหารชนิดใด");
        }else {
            foodLoseTextView.setText(foodLose);
        }
        bodyTextView.setText(body);
        numYhinTextView.setText(numYhin);
        numYhangTextView.setText(numYhang);

        if(gender.equals("m")) {
            genderTextView.setText("ชาย");
        }
        else {
            genderTextView.setText("หญิง");
        }


//        image = "5d51cd67d28ae.jpg";
        if(image.isEmpty()) {
           userProfile.setImageResource(R.drawable.user);
        } else {
            String url = "http://pilot.cp.su.ac.th/usr/u07580536/yhinyhang/images/" + image;
            Picasso.get().load(url).into(userProfile);
        }


        layout_Gotoquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Question.class));
            }
        });

        //Refresh
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//           public void onRefresh() {
//
//
//                Toast.makeText(getContext(),"Refresh",Toast.LENGTH_SHORT).show();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//
//                    }
//                }, 200);
//
//            }
//        });


        Call<User> call = retro.getApi().getYinyang(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                String email =    user.getEmail();
                String username  = user.getUsername();
                String gender =     user.getGender();
                String birthday =    user.getBirthday();
                String element =    user.getElement();
                String food =    user.getFood();
                String yin =    user.getNum_yhin();
                String yang =    user.getNum_yhang();

                numYhinTextView.setText(yin);
                numYhangTextView.setText(yang);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("getyinyang",t.getMessage());

            }
        });

        return view;
    }

}

