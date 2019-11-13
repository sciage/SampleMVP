package com.example.sampleapp;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.sampleapp.rx.BaseActivity;
import com.example.sampleapp.rx.PeoplePojo;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityMvpView {

    @Inject
    MainActivityMvpPresenter<MainActivityMvpView> mPresenter;

    MyRecyclerViewAdapter adapter;
    private List<PeoplePojo> data;

    @BindView(R.id.progressbar) View progressFrame;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       // setContentView(R.layout.activity_sample);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(MainActivity.this);

        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("MainActivity");

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
        //infiniteScrollProvider.attach(recyclerView,this);

        adapter = new MyRecyclerViewAdapter(this);

        recyclerView.setAdapter(adapter);

        progressFrame.setVisibility(View.VISIBLE);
        mPresenter.getData();



    }

    @Override
    protected void setUp() {

    }

    @Override
    public void getAllData(List<PeoplePojo> response) {
        data = response;
        adapter.addAll(response);

        progressFrame.setVisibility(View.INVISIBLE);
    }
}
