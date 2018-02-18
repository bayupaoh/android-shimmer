package me.bayupaoh.base.ui

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder


abstract class BaseActivity : AppCompatActivity() {

    protected var mContext: Context = this
    var toolbar: Toolbar? = null
        protected set
    protected var mInflater: LayoutInflater? = null
    protected var mActionBar: ActionBar? = null
    protected var unbinder: Unbinder? = null

    private val baseFragmentManager: FragmentManager
        get() = super.getSupportFragmentManager()

    protected abstract val resourceLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resourceLayout)
        mInflater = LayoutInflater.from(mContext)
        unbinder = ButterKnife.bind(this)
        onViewReady(savedInstanceState)
    }

    protected fun setupToolbar(basetoolbar: Toolbar, needHomeButton: Boolean) {
        setupToolbar(basetoolbar, needHomeButton, null)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun setupToolbar(basetoolbar: Toolbar, needHomeButton: Boolean, onClickListener: View.OnClickListener?) {

        toolbar = basetoolbar
        setSupportActionBar(basetoolbar)
        mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(needHomeButton)
            supportActionBar!!.setDisplayShowTitleEnabled(true)
        }

        if (onClickListener != null)
            basetoolbar.setNavigationOnClickListener(onClickListener)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun setupToolbarWithoutBackButton(toolbar: Toolbar) {

        this.toolbar = toolbar
        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar!!.setHomeButtonEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun setTitle(title: Int) {
        super.setTitle(title)
        if (mActionBar != null)
            mActionBar!!.title = getString(title)
    }

    override fun onBackPressed() {
        if (baseFragmentManager.backStackEntryCount > 0) {
            baseFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    protected abstract fun onViewReady(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        if (unbinder != null) {
            unbinder!!.unbind()
        }
    }

}
