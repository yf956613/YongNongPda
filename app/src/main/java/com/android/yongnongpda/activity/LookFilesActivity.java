package com.android.yongnongpda.activity;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.yongnongpda.R;
import com.android.yongnongpda.bean.CodeBean;
import com.android.yongnongpda.utils.AssistHelper;
import com.android.yongnongpda.utils.CommonListAdapter;
import com.android.yongnongpda.utils.ViewHolder;
import com.winsafe.mylibrary.view.AppBaseActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class LookFilesActivity extends AppBaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<CodeBean> list = new ArrayList<>();

    @Override
    protected int activityLayoutId() {
        return R.layout.activity_look_code;
    }

    @Override
    protected void initView() {
        setHeader("条码查看", true, false, 0, "", null);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        getData();
    }

    private void getData() {
        list= DataSupport.where("userName=?", AssistHelper.getUserName()).find(CodeBean.class);
        recyclerView.setAdapter(new CommonListAdapter<CodeBean>(this, R.layout.activity_code_item, list) {
            @Override
            public void convert(ViewHolder holder, CodeBean codeBean) {
                holder.setText(R.id.tvCode, codeBean.getCode());
            }
        });

//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                super.onDraw(c, parent, state);
//            }
//
//            @Override
//            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                super.onDrawOver(c, parent, state);
//            }
//
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//            }
//        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View view) {

    }

}
