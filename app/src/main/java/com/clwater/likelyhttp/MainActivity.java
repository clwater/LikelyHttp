package com.clwater.likelyhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.clwater.library.BaseEntity;
import com.clwater.library.BaseObserver;
import com.clwater.library.BaseObserverInterface;
import com.clwater.library.LikelyHttp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    BaseNetApi baseNetApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void init() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(LogInterceptor())
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                //添加gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                //添加rxjava转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        baseNetApi = mRetrofit.create(BaseNetApi.class);
    }

    private void test() {

        LikelyHttp.getInstance().setUniteDeal(new BaseObserverInterface() {
            @Override
            public void onRequestStart() {
                Toast.makeText(MainActivity.this, "onRequestStart", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRequestEnd() {
                Toast.makeText(MainActivity.this, "onRequestEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeError(int errorCode) {
                Toast.makeText(MainActivity.this, "onCodeError: " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });

        LikelyHttp.getInstance().start(baseNetApi.getHome(), new BaseObserver<String>(){
            @Override
            protected void onSuccees(BaseEntity<String> t) throws Exception {
                Toast.makeText(MainActivity.this, "onSuccees", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w("likelyhttp", "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
