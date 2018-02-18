package me.bayupaoh.feature.member

import me.bayupaoh.BaseApplication
import me.bayupaoh.api.APIService
import me.bayupaoh.base.presenter.BasePresenter
import me.bayupaoh.model.Results
import me.bayupaoh.model.User
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

/**
 * Created by DODYDMW19 on 1/30/2018.
 */

class MemberPresenter : BasePresenter<MemberView> {
    private var mvpView: MemberView? = null

    @Inject
    lateinit var apiService: APIService

    private var mCompositeDisposable: CompositeDisposable? = null

    constructor() {
        BaseApplication.applicationComponent.inject(this)
    }

    fun getMember(currentPage: Int?) {
        mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(
                apiService.getMembers(10, currentPage!!)
                        .map {
                            saveToCache(it.arrayData, currentPage)
                            it
                        }
                        .doOnError { t -> if (currentPage == 1) getCache() else Flowable.empty() }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ data ->
                            if (data.arrayData?.size!! > 0) {
                                mvpView?.showMembers(data.arrayData!!)
                            }
                        }, {
                            try {
                                it?.message?.let { mvpView?.onFailed(it) }
                            } catch (error: Exception) {
                                mvpView?.onFailed("An occur errors..")
                            }
                        })
        )

    }

    private fun saveToCache(datas: List<User>?, page: Int) {
        val realm = Realm.getDefaultInstance()

        if (datas == null && page == 1) {
            realm.executeTransaction {
                it.delete(User::class.java)
            }
        }
        if (!realm.isInTransaction) {
            realm.beginTransaction()
        }
        realm.copyToRealmOrUpdate(datas)
        realm.commitTransaction()
        realm.close()
    }

    fun getCache(): Flowable<Results<User>> {
        val resultWraper: Results<User> = Results()
        val realm = Realm.getDefaultInstance()

        val datas: RealmResults<User>? = realm.where(User::class.java).findAll()

        datas.let {
            resultWraper.page = 1
            resultWraper.totalPages = datas?.size?.div(10)
            resultWraper.perPage = 10
            resultWraper.total = datas?.size
            resultWraper.arrayData = datas
        }

        return Flowable.just(resultWraper)
    }

    override fun attachView(view: MemberView) {
        mvpView = view
    }

    override fun detachView() {
        mvpView = null
        mCompositeDisposable.let {
            mCompositeDisposable?.clear()
        }
    }
}
