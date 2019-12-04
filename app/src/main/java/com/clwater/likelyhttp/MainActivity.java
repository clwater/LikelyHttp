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

    private BaseNetApi baseNetApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        findViewById(R.id.get_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSuccess();
            }
        });

        findViewById(R.id.get_fail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFail();
            }
        });

        findViewById(R.id.get_code_fail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCodeFail();
            }
        });

        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMethod();
            }
        });

        findViewById(R.id.inio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnInIO();
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

        //请求统一处理
        //包含请求开始,完成,请求状态码异常
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
        })
        //设置数据成功状态码(毕竟有的人一定要用0做成功的返回码)
        .setSuccessCode(200);

    }

    private void getSuccess() {
        LikelyHttp.getInstance().start(baseNetApi.getSuccess(), new BaseObserver<String>(){
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


    private void getFail() {
        LikelyHttp.getInstance().start(baseNetApi.getFail(), new BaseObserver<String>(){
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

    private void getCodeFail() {
        LikelyHttp.getInstance().start(baseNetApi.getCodeFail(), new BaseObserver<String>(){
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

    private void postMethod() {
        LikelyHttp.getInstance().start(baseNetApi.getSuccess(), new BaseObserver<String>(){
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

    private void returnInIO() {
        LikelyHttp.getInstance().start(baseNetApi.getSuccess(), new BaseObserver<String>(){
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
