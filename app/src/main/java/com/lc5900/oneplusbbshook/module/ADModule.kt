package com.lc5900.oneplusbbshook.module

import android.content.Context
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class ADModule (loader: ClassLoader, mContext: Context) : BaseModule(loader, mContext){
    fun hookAD(){
        hookWelcomeActivity()
    }
    private fun hookWelcomeActivity(){
        var findClass =
            XposedHelpers.findClass("com.oneplus.bbs.ui.activity.WelcomeActivity", loader)
        XposedHelpers.findAndHookMethod("com.oneplus.bbs.ui.activity.WelcomeActivity",
            loader,
            "initView",object : XC_MethodHook(){
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    var declaredMethod = findClass.getDeclaredMethod("a")
                    declaredMethod.isAccessible = true
                    XposedHelpers.callMethod(param!!.thisObject, "a")
                }
            })
    }
}