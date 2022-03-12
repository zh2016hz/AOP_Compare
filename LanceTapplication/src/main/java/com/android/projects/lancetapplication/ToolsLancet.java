package com.android.projects.lancetapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.annotations.Insert;
import me.ele.lancet.base.annotations.Proxy;
import me.ele.lancet.base.annotations.TargetClass;

public class ToolsLancet {

    @TargetClass("com.android.projects.myjarlibrary.Tools")
    @Insert("evilCode")
    public static void evilCode() { //方法声明需要和原方法保持一致，如果有参数，参数也要保持一致（方法名、参数名不需要一致）
        try {
            Origin.callVoid(); //执行原有的流程
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("更改后数据", "evilCode: ！！！！！" );
        }

    }


        public static boolean isLogin = false;

        @TargetClass("androidx.appcompat.app.AppCompatActivity")
        @Proxy("onCreate")
        //onCreate(@Nullable Bundle savedInstanceState)
        protected void  onCreate(@Nullable Bundle fuck){
            if (isLogin) {
                Log.e("更改后数据", "onCreate: 来吧执行这个" );
            }
            Origin.callVoid();
            // 这里也可以 return null,毕竟系统也 return null
            Log.e("更改后数据", "我可以在里面为所欲为，做自己喜欢的事" );
            Log.d("更改后数据", Log.getStackTraceString(new Throwable()));
        }

}
