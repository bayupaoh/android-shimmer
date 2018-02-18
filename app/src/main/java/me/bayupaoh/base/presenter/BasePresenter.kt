package me.bayupaoh.base.presenter


interface BasePresenter<V> {

    fun attachView(view: V)

    fun detachView()

}
