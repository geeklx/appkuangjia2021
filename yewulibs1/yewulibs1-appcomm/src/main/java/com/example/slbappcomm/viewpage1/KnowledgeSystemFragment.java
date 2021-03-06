package com.example.slbappcomm.viewpage1;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slbappcomm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 知识体系
 * @author: zhukai
 * @date: 2019/3/31 20:39
 */
public class KnowledgeSystemFragment extends NewLazyFragment {

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private List<String> mData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TAG", "KnowledgeSystemFragment onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "KnowledgeSystemFragment onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TAG", "KnowledgeSystemFragment onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_viewpage1_knowledgesystem;
    }

    /**
     * 初始化视图
     *
     * @param view
     */
    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.rv_knowledgesystem);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mData = new ArrayList<>();
        showProgressDialog("请稍后");
        // 模拟数据的延迟加载
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    mData.add("知识体系文章" + (i + 1));
                }
                mAdapter = new ListAdapter(getActivity(), mData);
                mRecyclerView.setAdapter(mAdapter);
                hideProgressDialog();
            }
        }, 2000);
//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        for (int i = 0; i < 20; i++) {
//                            mData.add("知识体系文章" + (i + 1));
//                        }
//                        mAdapter = new ListAdapter(getActivity(), mData);
//                        mRecyclerView.setAdapter(mAdapter);
//                        hideProgressDialog();
//                    }
//                });
    }

    /**
     * 初始化事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", "KnowledgeSystemFragment onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG", "KnowledgeSystemFragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "KnowledgeSystemFragment onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", "KnowledgeSystemFragment onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TAG", "KnowledgeSystemFragment onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG", "KnowledgeSystemFragment onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "KnowledgeSystemFragment onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG", "KnowledgeSystemFragment onDetach()");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("TAG", "KnowledgeSystemFragment isVisibleToUser = " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("TAG", "KnowledgeSystemFragment onHiddenChanged = " + hidden);
    }
}
