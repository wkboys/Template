package com.template.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.template.boundary.UserDatabase
import com.template.module_common.BaseApp
import com.template.pagingpage.User

class AnthorUserViewModel(application: Application?) : AndroidViewModel(
    application!!
) {
    private val user: LiveData<AuthorUser>
    private val userRepository: UserRepository
    private val userName = "MichaelYe"
    fun getUser(): LiveData<AuthorUser> {
        return user
    }

    fun refresh() {
        userRepository.refresh(userName)
    }

    init {



        val database =    AnthorUserDatabase.getInstance(BaseApp.baseApplication)
        val userDao = database!!.userDao()
        userRepository = UserRepository(userDao, AnthorRetrofitClient.instance!!.api)
        user = userRepository.getUser(userName)
    }
}