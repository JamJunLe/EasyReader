package com.laotan.easyreader.ui.fragment.home.child.zhihu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.utils.SPUtils;
import com.laotan.easyreader.R;
import com.laotan.easyreader.adapter.ZhiHuCommentAdapter;
import com.laotan.easyreader.bean.zhihu.CommentBean;
import com.laotan.easyreader.presenter.ZhihuCommentPresenter;
import com.laotan.easyreader.presenter.impl.ZhihuCommentPresenterImpl;
import com.laotan.easyreader.ui.activity.zhihu.ZhiHuCommentActivity;
import com.laotan.easyreader.ui.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by quantan.liu on 2017/3/25.
 */

public class ZhiHuCommentFragment extends BaseFragment<ZhihuCommentPresenterImpl> implements ZhihuCommentPresenter.View {

    @BindView(R.id.rv_zhihu_comment)
    RecyclerView rvZhihuComment;

    private boolean isShort;
    public ZhiHuCommentFragment( boolean isShort) {
        this.isShort = isShort;
    }

    @Override
    protected void initView() {
        rvZhihuComment.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void loadData() {
        ZhiHuCommentActivity mZhiHuCommentActivity = (ZhiHuCommentActivity) getActivity();
        int id = mZhiHuCommentActivity.getId();
        if (isShort) {//懒加载在可见的时候加载，会让非静态变量最终都是同一个值所以只能用静态变量。
            mPresenter.fetchShortCommentInfo(id);
        } else {
            mPresenter.fetchLongCommentInfo(id);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_comment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void showRecyclerView(List<CommentBean.CommentsBean> list) {
        ZhiHuCommentAdapter zhiHuCommentAdapter = new ZhiHuCommentAdapter(list);
        rvZhihuComment.setAdapter(zhiHuCommentAdapter);
    }
}
