package com.android.projects.epic_application

class TestThread {

    fun startFirstThread() {
        Thread(mRunnable).apply {
            name="XXXXX"
        }.start()
    }

    fun startSecondThread() {
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
