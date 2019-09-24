package com.example.jittanan.yhinyhang.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jittanan.yhinyhang.Adapter.CustomAdapter;
import com.example.jittanan.yhinyhang.DetailActivity;
import com.example.jittanan.yhinyhang.R;
import com.example.jittanan.yhinyhang.api.RetrofitClient;
import com.example.jittanan.yhinyhang.models.Menu;
import com.example.jittanan.yhinyhang.models.Menuresponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_foodcomment extends Fragment {

    ListView listmenu;

    public Fragment_foodcomment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_foodcomment, container, false);

        listmenu = view.findViewById(R.id.list_menu);
        getToServer(view);
        // Inflate the layout for this fragment
        return view;
    }

    public void getToServer(final View view) {
        Call<Menuresponse> call = RetrofitClient.getInstance().getApi().getmenu();
        call.enqueue(new Callback<Menuresponse>() {
            @Override
            public void onResponse(Call<Menuresponse> call, Response<Menuresponse> response) {
                Menuresponse res = response.body();
                List<Menu> menu = res.getMenu();

                if (res.isStatus() == true) {

                    if (menu.size() != 0) {
                        CustomAdapter cus = new CustomAdapter(view.getContext(), R.layout.listview_row, menu);
                        listmenu.setAdapter(cus);

                    }
                }

            }

            @Override
            public void onFailure(Call<Menuresponse> call, Throwable t) {

            }
        });

        listmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
    }


}
