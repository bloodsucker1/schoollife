package com.example.work.fragment;

import com.example.work.R;
import com.example.work.interfaces.UIoperator;
import com.example.work.utils.UIUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Administrator on 2016/6/19.
 */
public abstract class BaseFragment extends Fragment implements UIoperator{

    protected View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(setLayoutId(),null);
        initView();
        initListener();
        initData();
        return root;
    }

    /**查找view
     * @param id
     * @param <T>
     * @return
     */
    public <T> T findView(int id){
        @SuppressWarnings("unchecked")
		T view = (T)root.findViewById(id);
        return view;
    }
    public void showtoast(String text){
        UIUtils.showtoast(getActivity(),text);
    }

    public void onClick(View v){
        switch (v.getId()){
            default:
            	childonClick(v);
                break;
        }
    }
}
