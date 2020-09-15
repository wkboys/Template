package com.template.module_main.activity

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.template.module_common.base.BaseVmActivity
import com.template.module_common.common.clickNoRepeat
import com.template.module_common.utils.MetaDataUtils
import com.template.module_common.utils.PrefUtils
import com.template.module_common.utils.StatusUtils
import com.template.module_common.widget.DialogUtils
import com.template.module_main.R
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions



class MainActivity : BaseVmActivity(), EasyPermissions.PermissionCallbacks {


    override fun onCreate(savedInstanceState: Bundle?) {
        changeTheme()
        super.onCreate(savedInstanceState)
    }

    override fun init(savedInstanceState: Bundle?) {
        val theme = PrefUtils.getBoolean(Constants.SP_THEME_KEY,false)

        btn.clickNoRepeat {
            PrefUtils.setBoolean(Constants.SP_THEME_KEY, !theme)
            recreate()
        }
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    /**
     * 动态切换主题
     */
    private fun changeTheme() {
        val theme = PrefUtils.getBoolean(Constants.SP_THEME_KEY,false)
        if (theme) {
            setTheme(R.style.AppTheme_Night)
        } else {
            setTheme(R.style.AppTheme)
        }
    }

    /**
     * 沉浸式状态,随主题改变
     */
    override fun setSystemInvadeBlack() {
        val theme = PrefUtils.getBoolean(Constants.SP_THEME_KEY,false)
        if (theme) {
            StatusUtils.setSystemStatus(this, true, false)
        } else {
            StatusUtils.setSystemStatus(this, true, true)
        }
    }

}
