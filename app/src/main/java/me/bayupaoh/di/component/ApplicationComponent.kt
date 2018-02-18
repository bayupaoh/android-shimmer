package me.bayupaoh.di.component

import me.bayupaoh.di.module.APIServiceModule
import me.bayupaoh.di.scope.SuitCoreApplicationScope
import me.bayupaoh.feature.member.MemberPresenter
import dagger.Component

@SuitCoreApplicationScope
@Component(modules = [(APIServiceModule::class)])
interface ApplicationComponent {

    fun inject(memberPresenter: MemberPresenter)

}
