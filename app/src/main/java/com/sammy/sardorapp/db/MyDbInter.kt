package com.sammy.sardorapp.db

import com.sammy.sardorapp.models.User

interface MyDbInter {

    fun add(user: User)

    fun getAllDate():ArrayList<User>

}