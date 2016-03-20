package com.yagnesh.retrofitdemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.yagnesh.retrofitdemo.Api.ApiFactory;
import com.yagnesh.retrofitdemo.Api.StackOverflowAPI;
import com.yagnesh.retrofitdemo.model.StackOverflowQuestions;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        StackOverflowAPI apiFactory = ApiFactory.createRetroService();
        Call<StackOverflowQuestions> call = apiFactory.loadQuestions("android");
        //asynchronous call
        call.enqueue(new Callback<StackOverflowQuestions>() {
            @Override
            public void onResponse(Call<StackOverflowQuestions> call, Response<StackOverflowQuestions> response) {
                setProgressBarIndeterminateVisibility(false);
                ArrayAdapter<StackOverflowQuestions.Item> adapter = (ArrayAdapter<StackOverflowQuestions.Item>) getListAdapter();
                adapter.clear();
                adapter.addAll(response.body().getItems());
            }

            @Override
            public void onFailure(Call<StackOverflowQuestions> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rxjavamenu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getItemId() == R.id.rxjava) {
            startActivity(new Intent(MainActivity.this, MainActivityRxJava.class));
            overridePendingTransition(0, 0);
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
