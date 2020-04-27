package com.lc5900.oneplusbbshook.module

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.widget.Toast
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage



object ApplicationModule {
    fun hookApplicationContext(lpparam: XC_LoadPackage.LoadPackageParam, body: (Context, ClassLoader) -> Unit) {
        XposedHelpers.findAndHookMethod(ContextWrapper::class.java, "attachBaseContext", Context::class.java, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam?) {
                val mContext = param!!.args[0] as Context
                body.invoke(mContext, lpparam.classLoader)
            }
        })
    }
}

