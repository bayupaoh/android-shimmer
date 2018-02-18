package me.bayupaoh.feature.member

import me.bayupaoh.base.presenter.MvpView
import me.bayupaoh.model.User

/**
 * Created by DODYDMW19 on 1/30/2018.
 */

interface MemberView : MvpView {

    fun showMembers(members: List<User>?)

    fun onFailed(message: String?)

}
