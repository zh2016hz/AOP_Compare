package com.android.projects.epic_application

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook
import java.io.BufferedReader
import java.io.InputStreamReader

object  HookUtils {
     fun checkPermissionAction(baseContext: Context?) {
        val inputStream =
                baseContext?.resources?.assets?.open("privacy_methods.json")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val result = StringBuilder()
        var line: String? = ""
        while (reader.readLine().also { line = it } != null) {
            result.append(line)
        }
        val configEntity = Gson().fromJson(result.toString(), PrivacyMethod::class.java)
        configEntity.methods.forEach {
            hookPrivacyMethod(it)
        }

    }

    private fun hookPrivacyMethod(entity: Method) {
        if (entity.name_regex.isNotEmpty()) {
            val methodName = entity.name_regex.substring(entity.name_regex.lastIndexOf(".") + 1)
            val className = entity.name_regex.substring(0, entity.name_regex.lastIndexOf("."))
            try {
                val lintClass = Class.forName(className)
                DexposedBridge.hookAllMethods(lintClass, methodName, object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                        super.beforeHookedMethod(param)
                        Log.i("epic", "______beforeHookedMethod $className.$methodName")
                        Log.d("epic", "stack= " + Log.getStackTraceString(Throwable()))
                    }
                })
            } catch (e: Exception) {
                Log.w("epic", "hookPrivacyMethod:$className.$methodName,e=${e.message}")
            }
        }
    }


}