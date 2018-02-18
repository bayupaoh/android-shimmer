package me.bayupaoh.feature.member

import android.content.Context
import android.view.ViewGroup
import me.bayupaoh.R
import me.bayupaoh.base.ui.adapter.BaseRecyclerAdapter
import me.bayupaoh.model.User

/**
 * Created by DODYDMW19 on 1/30/2018.
 */
class MemberAdapter(context: Context) : BaseRecyclerAdapter<User, MemberItemView>(context) {

    private var mOnActionListener: MemberItemView.OnActionListener? = null

    fun setOnActionListener(onActionListener: MemberItemView.OnActionListener) {
        mOnActionListener = onActionListener
    }

    override fun getItemResourceLayout(viewType: Int): Int = R.layout.item_member

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MemberItemView{
        val view = MemberItemView(getView(parent!!, viewType), mItemClickListener, mLongItemClickListener)
        mOnActionListener?.let { view.setOnActionListener(it) }
        return view
    }
}
