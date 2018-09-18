package com.lifesoft.chc.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifesoft.chc.chargingcontrol.R;
import com.lifesoft.chc.model.CCTransactions;
import com.lifesoft.chc.view.adapter.GeneralRecyclerViewAdapter;
import com.lifesoft.chc.view.sms.model.SmsObject;


public class AllSmsFragment extends Fragment {

    private View view;
    private Context context;
    private GeneralRecyclerViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private final CCTransactions model = SmsObject.INSTANCE().getCcTransactions();

    public AllSmsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_sms, container, false);
        context = getContext();
        initViews();
        initAdapter();
        return view;
    }

    private void initViews() {
        recyclerView = view.findViewById(R.id.recyclerViewAdapter);
    }

    private void initAdapter() {
        gridLayoutManager = new GridLayoutManager(context, 1);
        if (model != null) {
            adapter = new GeneralRecyclerViewAdapter(context, model);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

}
