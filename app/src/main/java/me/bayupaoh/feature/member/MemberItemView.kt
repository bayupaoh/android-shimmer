package me.bayupaoh.feature.member

import android.view.View
import butterknife.OnClick
import me.bayupaoh.R
import me.bayupaoh.base.ui.adapter.BaseRecyclerAdapter
import me.bayupaoh.base.ui.adapter.viewholder.BaseItemViewHolder
import me.bayupaoh.model.User
import kotlinx.android.synthetic.main.item_member.view.*

/**
 * Created by DODYDMW19 on 1/30/2018.
 */
class MemberItemView (itemView: View, mItemClickListener: BaseRecyclerAdapter.OnItemClickListener?, mLongItemClickListener: BaseRecyclerAdapter.OnLongItemClickListener?)
    : BaseItemViewHolder<User>(itemView, mItemClickListener, mLongItemClickListener){

    private var mActionListener: MemberItemView.OnActionListener? = null
    private var user: User? = null

    override fun bind(data: User?) {
        data.let {
            this.user = data
            itemView.imgMember.setImageURI(data?.avatar)
            itemView.txtMemberName.text = data?.firstName + " " + data?.lastName
        }
    }

    fun getData(): User{
        return user!!
    }

    @OnClick(R.id.button)
    internal fun onItemClicked() {
        if (mActionListener != null) {
            mActionListener?.onClicked(this)
        }
    }

    fun setOnActionListener(listener: OnActionListener) {
        mActionListener = listener
    }

    interface OnActionListener {
        fun onClicked(view: MemberItemView)
    }
}
