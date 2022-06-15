package com.android.projects.epic_application

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        HookUtils.checkPermissionAction(baseContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        testEpic()
//        startThread()
        Log.e("TAG", "onCreate: 333333333333" )
        Log.e("TAG", "onCreate: 11111111111提交" )
        Log.e("TAG", "onCreate: 44444444" )
        Log.e("TAG", "onCreate: 2222222222222" )

        Log.e("TAG", "onCreate: 小需求发现一堆代码不规范？我草，其实有些没必要更高？" )
    }



    private fun startThread() {
        TestThread().startFirstThread()
        Thread.sleep(2000)
        TestThread().startSecondThread()
    }

    private fun testEpic() {
        DexposedBridge.hookAllConstructors(Thread::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                super.afterHookedMethod(param)
                Log.d("更改后数据", Log.getStackTraceString(Throwable()))
                val thread = param.thisObject as Thread
                val clazz: Class<*> = thread.javaClass
                if (clazz != Thread::class.java) {
                    Log.d("Epic", "found class extend Thread:$clazz")
                    DexposedBridge.findAndHookMethod(clazz, "run", EpicUtils())
                }
                Log.d("Epic", "Thread:———————— " + thread.name + " class:" + thread.javaClass + "被创建.")
            }
        })
        DexposedBridge.findAndHookMethod(Thread::class.java, "run", EpicUtils())

    }
}