package com.clwater.library;

import android.content.Context;
import android.widget.Toast;
import io.reactivex.Observable;

/**
 * Create by clwater on 2019/11/24.
 */
public class Test<T> {
    public static void test(Context context){
        Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
    }

    public T a;

}
