package com.permissionx.library.simple

import androidx.fragment.app.FragmentActivity
import com.permissionx.library.fragment.InvisibleFragment
import com.permissionx.library.fragment.PermissionCallBack
import java.security.Permissions

/**
 * =================================================
 * @author   qi ji
 * @date     2021/8/19 10:59
 * @Email:   534438777@qq.com
 * @Content:
 * =================================================
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

//    支持申请任意多种权限
    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callBack: PermissionCallBack
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callBack, *permissions)
    }

}