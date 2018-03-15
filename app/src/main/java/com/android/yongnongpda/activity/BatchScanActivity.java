package com.android.yongnongpda.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.yongnongpda.R;
import com.android.yongnongpda.adapter.CommonNameAdapter;
import com.android.yongnongpda.bean.db.OrganBean;
import com.android.yongnongpda.bean.db.ProductBean;
import com.android.yongnongpda.bean.db.WarehouseBean;
import com.android.yongnongpda.utils.AssistHelper;
import com.winsafe.mylibrary.view.AppBaseActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shijie.yang on 2018/2/27.
 */

public class BatchScanActivity extends AppBaseActivity{
    private final int DIALOG_01 = 1; //发货机构
    private final int DIALOG_02 = 2; //发货仓库
    private final int DIALOG_03 = 3; //收货机构
    private final int DIALOG_04 = 4; //收货仓库
    private final int DIALOG_05 = 5; //产品

    @BindView(R.id.btnReset)
    Button btnReset;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.tvSendOrgan)
    TextView tvSendOrgan;
    @BindView(R.id.tvSendWarehouse)
    TextView tvSendWarehouse;
    @BindView(R.id.tvReceiveOrgan)
    TextView tvReceiveOrgan;
    @BindView(R.id.tvReceiveWarehouse)
    TextView tvReceiveWarehouse;
    @BindView(R.id.tvProduct)
    TextView tvProduct;
    @BindView(R.id.etBoxNum)
    EditText etBoxNum;
    @BindView(R.id.etTuoNum)
    EditText etTuoNum;

    private ListView lvData;
    private List<OrganBean> sendOrganList = new ArrayList<>();
    private List<OrganBean> receiveOrganList = new ArrayList<>();
    private List<WarehouseBean> sendWareHeList = new ArrayList<>();
    private List<WarehouseBean> receiveWareHeList = new ArrayList<>();
    private List<ProductBean> productList = new ArrayList<>();
    private CommonNameAdapter adapter;

    private String sendOrganName="",sendOrganCode="",rvOrganName="",rvOrganCode="",
            sendWarehouseName="",sendWarehouseCode="",receiveWarehouseName="",receiveWarehouseCode="",
            productNo="",productId="",productName="";
    private int boxNum,tuoNum;


    @Override
    protected int activityLayoutId() {
        return R.layout.activity_batch_scan;
    }

    @Override
    protected void initView() {
        setHeader("批量扫描", true, false, 0, "", null);
    }

    @Override
    protected void setListener() {

    }

    //发货机构list
    private void getSendOrganList(String key) {
        List<OrganBean> list = DataSupport.where("type=?", "1").find(OrganBean.class);
        OrganBean bean;
        sendOrganList.clear();
        for (int i = 0; i < list.size(); i++) {
            bean = new OrganBean();
            bean.setName(list.get(i).getName());
            bean.setCode(list.get(i).getCode());
            bean.setOrganId(list.get(i).getOrganId());
            //检索条件
            if ("".equals(key) || list.get(i).getName().contains(key)) {
                sendOrganList.add(bean);
            }
        }
        adapter = new CommonNameAdapter<OrganBean>(this, sendOrganList) {
            @Override
            public void showText(ViewHolder holder, OrganBean organBean, TextView textView) {
                textView.setText(organBean.getName());
            }
        };
        lvData.setAdapter(adapter);
    }

    //发货仓库list
    private void getSendWarehouseList(String key) {
        List<WarehouseBean> list = DataSupport.where("type=? and code=?", "1",sendOrganCode).find(WarehouseBean.class);
        WarehouseBean bean;
        sendWareHeList.clear();
        for (int i = 0; i < list.size(); i++) {
            bean = new WarehouseBean();
            bean.setName(list.get(i).getName());
            bean.setCode(list.get(i).getCode());
            bean.setWarehouseId(list.get(i).getWarehouseId());
            //检索条件
            if ("".equals(key) || list.get(i).getName().contains(key)) {
                sendWareHeList.add(bean);
            }
        }
        adapter = new CommonNameAdapter<WarehouseBean>(this, sendWareHeList) {
            @Override
            public void showText(ViewHolder holder, WarehouseBean warehouseBean, TextView textView) {
                textView.setText(warehouseBean.getName());
            }
        };
        lvData.setAdapter(adapter);
    }

    //收货机构list
    private void getReceiveOrganList(String key) {
        List<OrganBean> list = DataSupport.where("type=?", "2").find(OrganBean.class);
        OrganBean bean;
        receiveOrganList.clear();
        for (int i = 0; i < list.size(); i++) {
            bean = new OrganBean();
            bean.setName(list.get(i).getName());
            bean.setCode(list.get(i).getCode());
            bean.setOrganId(list.get(i).getOrganId());
            //检索条件
            if ("".equals(key) || list.get(i).getName().contains(key)) {
                receiveOrganList.add(bean);
            }
        }
        adapter = new CommonNameAdapter<OrganBean>(this, receiveOrganList) {
            @Override
            public void showText(ViewHolder holder, OrganBean organBean, TextView textView) {
                textView.setText(organBean.getName());
            }
        };
        lvData.setAdapter(adapter);
    }

    //收货仓库list
    private void getReceiveWarehouseList(String key) {
        List<WarehouseBean> list = DataSupport.where("type=? and code=?", "2",rvOrganCode).find(WarehouseBean.class);
        WarehouseBean bean;
        receiveWareHeList.clear();
        for (int i = 0; i < list.size(); i++) {
            bean = new WarehouseBean();
            bean.setName(list.get(i).getName());
            bean.setCode(list.get(i).getCode());
            bean.setWarehouseId(list.get(i).getWarehouseId());
            //检索条件
            if ("".equals(key) || list.get(i).getName().contains(key)) {
                receiveWareHeList.add(bean);
            }
        }
        adapter = new CommonNameAdapter<WarehouseBean>(this, receiveWareHeList) {
            @Override
            public void showText(ViewHolder holder, WarehouseBean warehouseBean, TextView textView) {
                textView.setText(warehouseBean.getName());
            }
        };
        lvData.setAdapter(adapter);
    }

    //产品
    private void getProductList(String key) {
        List<ProductBean> list = DataSupport.findAll(ProductBean.class);
        ProductBean bean;
        productList.clear();
        for (int i = 0; i < list.size(); i++) {
            bean = new ProductBean();
            bean.setSpec(list.get(i).getSpec());
            bean.setName(list.get(i).getName());
            bean.setProductId(list.get(i).getProductId());

            //检索条件
            if ("".equals(key) || list.get(i).getName().contains(key)) {
                productList.add(bean);
            }
        }
        adapter = new CommonNameAdapter<ProductBean>(this, productList) {
            @Override
            public void showText(ViewHolder holder, ProductBean productBean, TextView textView) {
                textView.setText(productBean.getName());
            }
        };
        lvData.setAdapter(adapter);
    }


    @OnClick({R.id.btnReset, R.id.btnNext, R.id.tvSendOrgan, R.id.tvSendWarehouse,
            R.id.tvReceiveOrgan, R.id.tvReceiveWarehouse, R.id.tvProduct})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnReset:
                tvSendOrgan.setText("");
                tvSendWarehouse.setText("");
                tvReceiveOrgan.setText("");
                tvReceiveWarehouse.setText("");
                tvProduct.setText("");
                etBoxNum.setText("");
                etTuoNum.setText("");
                break;
            case R.id.btnNext:
                boxNum=Integer.valueOf(etBoxNum.getText().toString().trim());
                tuoNum=Integer.valueOf(etTuoNum.getText().toString().trim());
                Bundle bundle=new Bundle();
                bundle=AssistHelper.bundleString(bundle,sendOrganName,sendOrganCode,rvOrganName,rvOrganCode,
                    sendWarehouseName,sendWarehouseCode,receiveWarehouseName,receiveWarehouseCode,
                    productNo,productId,productName,boxNum,tuoNum);
                openActivity(BatchScanActivity.this, ScanMessageActivity.class,bundle, false);
                break;
            case R.id.tvSendOrgan:
                dialog("发货机构", tvSendOrgan, DIALOG_01);

                break;
            case R.id.tvSendWarehouse:
                dialog("发货仓库", tvSendWarehouse, DIALOG_02);
                break;
            case R.id.tvReceiveOrgan:
                dialog("收货机构", tvReceiveOrgan, DIALOG_03);
                break;
            case R.id.tvReceiveWarehouse:
                dialog("收货仓库", tvReceiveWarehouse, DIALOG_04);
                break;
            case R.id.tvProduct:
                dialog("产品", tvProduct, DIALOG_05);
                break;

            default:
                break;

        }
    }

    //select dialog
    private void dialog(String msg, final TextView text, final int flag) {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_data_dialog, null);
        final Dialog dialog = new AlertDialog.Builder(this)
                .setTitle(msg)
                .setView(view)
                .setPositiveButton("取消", null).create();
        EditText etSearch = view.findViewById(R.id.etSearch);
        lvData = view.findViewById(R.id.lvData);
        //get list
        if (flag == DIALOG_01) {
            getSendOrganList("");
        } else if (flag == DIALOG_02) {
            getSendWarehouseList("");
        } else if (flag == DIALOG_03) {
            getReceiveOrganList("");
        } else if (flag == DIALOG_04) {
            getReceiveWarehouseList("");
        } else if (flag == DIALOG_05) {
            getProductList("");
        }
        search(etSearch, flag);
        onItemListener(dialog,text,flag);

        dialog.setCancelable(true);
        dialog.show();
    }
    //onclick item show text
    private void onItemListener(final Dialog dialog,final TextView text,final int flag){
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                if (flag == DIALOG_01) {
                    OrganBean s = sendOrganList.get(position);
                    sendOrganName=s.getName();//机构名称
                    sendOrganCode=s.getOrganId();//机构id
                    text.setText(s.getName());

                    //选择机构如果此时仓库下一条数据
                    List<WarehouseBean> list = DataSupport.where("type=? and code=?", "1",sendOrganCode).find(WarehouseBean.class);
                    if (list.size()==1) {
                        sendWarehouseName=list.get(0).getName(); //仓库名称
                        sendWarehouseCode=list.get(0).getWarehouseId(); //仓库编号
                        tvSendWarehouse.setText(sendWarehouseName);
                    }

                } else if (flag == DIALOG_02) {
                    WarehouseBean w = sendWareHeList.get(position);
                    sendWarehouseName=w.getName();
                    sendWarehouseCode=w.getWarehouseId();
                    text.setText(w.getName());
                } else if (flag == DIALOG_03) {
                    OrganBean s = receiveOrganList.get(position);
                    rvOrganName=s.getName();
                    rvOrganCode=s.getOrganId();
                    text.setText(s.getName());

                    //选择机构如果此时仓库下一条数据
                    List<WarehouseBean> list = DataSupport.where("type=? and code=?", "2",rvOrganCode).find(WarehouseBean.class);
                    if (list.size()==1) {
                        receiveWarehouseName=list.get(0).getName();
                        receiveWarehouseCode=list.get(0).getWarehouseId();
                        tvReceiveWarehouse.setText(receiveWarehouseName);
                    }

                } else if (flag == DIALOG_04) {
                    WarehouseBean w = receiveWareHeList.get(position);
                    receiveWarehouseName=w.getName();
                    receiveWarehouseCode=w.getWarehouseId();
                    text.setText(w.getName());
                } else if (flag == DIALOG_05) {
                    ProductBean p = productList.get(position);
                    productNo=p.getSpec();
                    productId=p.getProductId();
                    productName=p.getName();
                    text.setText(p.getName());
                }
            }
        });

    }
    //search
    private void search(EditText etSearch, final int flag) {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString();
                if (flag == DIALOG_01) {
                    getSendOrganList(query);
                } else if (flag == DIALOG_02) {
                    getSendWarehouseList(query);
                } else if (flag == DIALOG_03) {
                    getReceiveOrganList(query);
                } else if (flag == DIALOG_04) {
                    getReceiveWarehouseList(query);
                } else if (flag == DIALOG_05) {
                    getProductList(query);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
