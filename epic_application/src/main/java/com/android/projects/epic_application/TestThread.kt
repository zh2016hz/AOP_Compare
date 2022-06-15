package com.android.projects.epic_application

import android.util.Log

class TestThread {

    fun startFirstThread() {
        Thread(mRunnable).apply {
            name="XXXXX"
        }.start()
    }

    fun startSecondThread() {
        Log.e("TAG", "onCreate: 做国金需求" )
        CustomThread().apply {
            name="SSSSSS"
        }.start()
    }
}

// 继承Thread
class CustomThread : Thread() {
    // 重写run()方法
    override fun run() {
        super.run()
        Thread.sleep(1000)
        println("延迟122222s打印")
    }
}

val mRunnable = Runnable {
    run {
        Thread.sleep(5000)
        println("5s11111后打印输出")
    }
}
