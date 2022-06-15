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
        Log.e("TAG", "onCreate: 最近在做商汤OCR需求" )
        Log.e("TAG", "onCreate: 遇到一堆问题我草" )


        Log.e("TAG", "onCreate: 妈的。产品需求变来变去，我草，导致时间不够" )
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