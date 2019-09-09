package com.example.jittanan.yhinyhang.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.jittanan.yhinyhang.Adapter.SearchAdapter;
import com.example.jittanan.yhinyhang.R;
import com.example.jittanan.yhinyhang.api.RetrofitClient;
import com.example.jittanan.yhinyhang.models.Menuresponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_search extends Fragment {

    ListView lst_view_search;

    public Fragment_search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        lst_view_search = view.findViewById(R.id.lstView);
        setHasOptionsMenu(true);

        initSearch(view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initSearch(final View view) {
        Call<Menuresponse> call = RetrofitClient.getInstance().getApi().getmenu();
        call.enqueue(new Callback<Menuresponse>() {
            @Override
            public void onResponse(Call<Menuresponse> call, Response<Menuresponse> response) {
                Menuresponse res = response.body();

                if(res.isStatus() == true) {
                    if(res.getMenu() != null) {
                        SearchAdapter se = new SearchAdapter(view.getContext(),R.layout.listview_search, res.getMenu());
                        lst_view_search.setAdapter(se);
                    }
                }
            }

            @Override
            public void onFailure(Call<Menuresponse> call, Throwable t) {
                Log.e("Get menu search", t.getMessage());
            }
        });
    }
}
