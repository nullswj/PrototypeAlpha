package com.swj.prototypealpha.swj;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swj.prototypealpha.R;
import com.swj.prototypealpha.swj.util.ItemBean;
import com.swj.prototypealpha.swj.util.RecyclerViewHelper.ItemAdapter;

import java.util.ArrayList;
import java.util.List;


public class ProjFragment extends Fragment {
    private RecyclerView recvv_proinfo;

    private ItemAdapter adapter;

    private List<ItemBean> itemList = new ArrayList<>();

    private void initUI()
    {
        recvv_proinfo = getActivity().findViewById(R.id.recv_start_pro_info);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recvv_proinfo.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(itemList);

        recvv_proinfo.setAdapter(adapter);
        Update();
    }

    private void Update()
    {
        itemList.clear();
        Bitmap leftImage = BitmapFactory.decodeResource(getResources(),R.mipmap.detail);
        Bitmap rightArrow = BitmapFactory.decodeResource(getResources(),R.mipmap.right_arrow);
        ItemBean item0 = new ItemBean("项目名称","橘子洲大桥提质改造工程",leftImage,rightArrow);
        ItemBean item1 = new ItemBean("项目地址","岳麓区",leftImage,rightArrow);
        ItemBean item3 = new ItemBean("开工时间","2018.09",leftImage,rightArrow);
        ItemBean item2 = new ItemBean("建设单位","长沙市工务局",leftImage,rightArrow);
        ItemBean item6 = new ItemBean("施工单位","湖南省绿林市政景观工程有限公司",leftImage,rightArrow);
        ItemBean item4 = new ItemBean("监理单位","城规监理",leftImage,rightArrow);
        ItemBean item5 = new ItemBean("当前进度","已完成20个站点土建工程，7个站点钢结构施工",leftImage,rightArrow);
        ItemBean item7 = new ItemBean("检查记录","2月18日，执法服务",leftImage,rightArrow);

        itemList.add(item0);
        adapter.notifyItemChanged(0);
        itemList.add(item1);
        adapter.notifyItemChanged(1);
        itemList.add(item2);
        adapter.notifyItemChanged(2);
        itemList.add(item3);
        adapter.notifyItemChanged(3);
        itemList.add(item4);
        adapter.notifyItemChanged(4);
        itemList.add(item5);
        adapter.notifyItemChanged(5);
        itemList.add(item6);
        adapter.notifyItemChanged(6);
        itemList.add(item7);
        adapter.notifyItemChanged(7);

        adapter.notifyItemChanged(0,itemList.size());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_proj, container, false);
    }


}
