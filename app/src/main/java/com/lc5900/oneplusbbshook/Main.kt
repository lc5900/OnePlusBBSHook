package com.lc5900.oneplusbbshook

import com.lc5900.oneplusbbshook.module.ADModule
import com.lc5900.oneplusbbshook.module.ApplicationModule
import com.lc5900.oneplusbbshook.module.PhoneNameModule
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

class Main : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if ("com.oneplus.bbs" != lpparam.packageName) {
            return
        }
        ApplicationModule.hookApplicationContext(lpparam){
            mContext,mClassLoader ->
            PhoneNameModule(mClassLoader,mContext).hookPhoneName()
            ADModule(mClassLoader,mContext).hookAD()
        }

    }
}