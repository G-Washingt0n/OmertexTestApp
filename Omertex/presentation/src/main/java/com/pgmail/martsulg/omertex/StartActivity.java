package com.pgmail.martsulg.omertex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.pgmail.martsulg.domain.ElementsModel;
import com.pgmail.martsulg.domain.ElementsUseCase;
import com.pgmail.martsulg.domain.UrlUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by lenovo on 25.10.2017.
 */

public class StartActivity extends FragmentActivity {

    public static final String URL_KEY = "url";
    public static final String LIST_KEY = "element";
    private ImageView imageView;
    private ImageView cross;
    private Button button;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<String> elementsList = new ArrayList<>();
    private ArrayList<String> urlList = new ArrayList<>();
    public static List<ElementsModel> elementsModels = new ArrayList<>();
    private StartAdapter adapter;
    private Fragment infoFragment = new InfoFragment();
    public UrlUseCase urlUseCase = new UrlUseCase();
    public ElementsUseCase elementsUseCase = new ElementsUseCase();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        cross = (ImageView) findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment(getSupportFragmentManager(),infoFragment);
                cross.setVisibility(View.GONE);
            }
        });
        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                elementsList.clear();
                urlList.clear();
                loadUrls();
            }
        });
    }


    private void setRecycler(){

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new StartAdapter(elementsList,urlList);

        adapter.setListener(new StartAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int item) {
                cross.setVisibility(View.VISIBLE);
                showInformation(item);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    private void loadUrls(){
        urlUseCase.execute(null, new DisposableObserver<ArrayList<String>>() {
            @Override
            public void onNext(@NonNull ArrayList<String> strings) {
            urlList = strings;
                loadElements();
            }
            @Override
            public void onError(@NonNull Throwable e) {}

            @Override
            public void onComplete() {
                urlUseCase.dispose();
            }
        });

    }

    private void loadElements(){
        elementsUseCase.execute(null, new DisposableObserver<List<ElementsModel>>() {
            @Override
            public void onNext(@NonNull List<ElementsModel> models) {
                elementsModels = models;
                for(int i=0;i<elementsModels.size();i++)
                    elementsList.add(elementsModels.get(i).getName());
                setRecycler();
            }

            @Override
            public void onError(@NonNull Throwable e) {}

            @Override
            public void onComplete() {
                elementsUseCase.dispose();
            }
        });
    }

    private void showInformation(int position){

        Bundle bundle = new Bundle();
        bundle.putString(URL_KEY,urlList.get(position));
        bundle.putInt(LIST_KEY,position);
        infoFragment.setArguments(bundle);
        showFragment(getSupportFragmentManager(), infoFragment);
    }

    public static void removeFragment(FragmentManager fragmentManager,Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}
