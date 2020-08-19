package com.lc5900.oneplusbbshook.module

import android.content.Context
import com.android.zgj.utils.MultiprocessSharedPreferences
import com.lc5900.oneplusbbshook.R
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers

class PhoneNameModule(loader: ClassLoader, mContext: Context) : BaseModule(loader, mContext){
    fun hookPhoneName(){
        hookPhoneNameDetail()
    }
    private fun hookPhoneNameDetail(){
        val string = "\n\n\n [color=#FC06F7]|[/color][color=#F90CEF] [/color][color=#F612E7]来[/color][color=#F318DF]自[/color][color=#F01ED7]：[/color][color=#ED24CF]O[/color][color=#EA2AC7]n[/color][color=#E730BF]e[/color][color=#E436B7]P[/color][color=#E13CAF]l[/color][color=#DE42A7]u[/color][color=#DB489F]s[/color][color=#D84E97]7[/color][color=#D5548F]P[/color][color=#D25A87]r[/color][color=#CF607F]o[/color][color=#CC6677]社[/color][color=#C96C6F]区[/color][color=#C67267]l[/color][color=#C3785F]c[/color][color=#C07E57]5[/color][color=#BD844F]9[/color][color=#BA8A47]0[/color][color=#B7903F]0[/color][color=#B49637]定[/color][color=#B19C2F]制[/color][color=#AEA227]版[/color][color=#ABA81F] [/color][color=#A8AE17]|[/color] "
        MultiprocessSharedPreferences.setAuthority("com.lc5900.oneplusbbshook.provider");
        var sharedPreferences= MultiprocessSharedPreferences.getSharedPreferences(
            mContext,
            "OnePlusBBS",
            Context.MODE_PRIVATE
        )
        var tail = sharedPreferences.getString("tail", string)
        XposedHelpers.findAndHookMethod(
            "com.oneplus.bbs.ui.activity.PostThreadsActivity",
            loader,
            "getPhoneName",
            object : XC_MethodReplacement() {
                @Throws(Throwable::class)
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return tail.toString()
                }
            })
        XposedHelpers.findAndHookMethod(
            "com.oneplus.bbs.ui.activity.ThreadsActivity",
            loader,
            "getPhoneName",
            object : XC_MethodReplacement() {
                @Throws(Throwable::class)
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return tail.toString()
                }
            })
    }
}