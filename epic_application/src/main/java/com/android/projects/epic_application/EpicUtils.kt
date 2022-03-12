package com.android.projects.epic_application

import android.util.Log
import de.robv.android.xposed.XC_MethodHook

class EpicUtils : XC_MethodHook() {
    override fun beforeHookedMethod(param: MethodHookParam?) {
        super.beforeHookedMethod(param)
        val t = param?.thisObject as Thread
        Log.i("Epic", "thread:${t}, started..")

    }

    override fun afterHookedMethod(param: MethodHookParam?) {
        super.afterHookedMethod(param)
        val t = param?.thisObject as Thread
        Log.i("Epic", "thread:${t}, end..")
    }

}