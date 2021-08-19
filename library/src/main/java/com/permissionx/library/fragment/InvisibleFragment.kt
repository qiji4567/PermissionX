package com.permissionx.library.fragment

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * =================================================
 * @author   qi ji
 * @date     2021/8/19 10:29
 * @Email:   534438777@qq.com
 * @Content:
 * =================================================
 */

typealias PermissionCallBack = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    private var callBack: PermissionCallBack? = null

    fun requestNow(cd: (Boolean, List<String>) -> Unit, vararg permissions: String) {
        callBack = cd
        requestPermissions(permissions, 1)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callBack?.let { it(allGranted, deniedList) }
        }
    }

}