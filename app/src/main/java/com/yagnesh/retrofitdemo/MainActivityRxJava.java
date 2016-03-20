package com.yagnesh.retrofitdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.yagnesh.retrofitdemo.Api.ApiFactory;
import com.yagnesh.retrofitdemo.Api.StackOverflowAPI;
import com.yagnesh.retrofitdemo.R;
import com.yagnesh.retrofitdemo.apirxjava.ApiFactoryRxJava;
import com.yagnesh.retrofitdemo.apirxjava.StackOverflowAPIRxJava;
import com.yagnesh.retrofitdemo.model.StackOverflowQuestions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivityRxJava extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // this.subscription = observable.subscribe(this);
        setContentView(R.layout.activity_main);
        ArrayAdapter<StackOverflowQuestions.Item> arrayAdapter =
                new ArrayAdapter<StackOverflowQuestions.Item>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new ArrayList<StackOverflowQuestions.Item>());
        setListAdapter(arrayAdapter);
        setProgressBarIndeterminateVisibility(true);
/*MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
File = new File("/storage/emulated/0/Pictures/MyApp/test.png");
RequestBody requestBody = RequestBody.create(MEDIA_TYPE_PNG, file);
*/
        StackOverflowAPIRxJava apiFactory = ApiFactoryRxJava.createRetroService();
        Observable<StackOverflowQuestions> call = apiFactory.loadQuestions("android");

        Subscription subscription = call
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StackOverflowQuestions>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            int code = response.code();
                        }
                    }

                    @Override
                    public void onNext(StackOverflowQuestions stackOverflowQuestions) {
                       /* setProgressBarIndeterminateVisibility(false);
                        ArrayAdapter<StackOverflowQuestions.Item> adapter = (ArrayAdapter<StackOverflowQuestions.Item>) getListAdapter();
                        adapter.clear();
                        adapter.addAll(stackOverflowQuestions.getItems());*/
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
