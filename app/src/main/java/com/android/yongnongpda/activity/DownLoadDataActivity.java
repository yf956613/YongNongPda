package com.android.yongnongpda.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.yongnongpda.R;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.bean.db.OrganBean;
import com.android.yongnongpda.bean.db.ProductBean;
import com.android.yongnongpda.bean.db.WarehouseBean;
import com.android.yongnongpda.confing.AppConfig;
import com.android.yongnongpda.utils.AssistHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import okhttp3.Call;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class DownLoadDataActivity {
    public static int FLAG = 0;


    public static void downLoadData(Context context, Handler handler) {
        downLoadSendOrgan(context, handler);
        downLoadReceiveOrgan(context, handler);
        downLoadSendWarehouse(context, handler);
        downLoadReceiveWarehouse(context, handler);
        downLoadProduct(context, handler);
    }

    //**********************************************************
    //下载发货机构
    public static void downLoadSendOrgan(final Context context, final Handler handler) {
        DataSupport.deleteAll(OrganBean.class, "type=?", "1");
        OkHttpUtils.post().url(AppConfig.URL_ORGAN)
                .addParams("type", "1")
                .addParams(AppConfig.USERNAME, AssistHelper.getUserName())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, String s) {
                String[] rt = s.split(";");
                OrganBean bean;
                for (int i = 0; i < rt.length; i++) {
                    Log.i("TAG", "发货机构 :" + rt[i]);
                    String[] result = rt[i].split(",");
                    bean = new OrganBean();
                    bean.setCode(result[0]);
                    bean.setName(result[1]);
                    bean.setOrganId(result[0]);
                    bean.setType("1");
                    bean.save();
                }
                FLAG++;
                Message message = new Message();
                message.what = FLAG;
                handler.sendMessage(message);
            }
        });
    }

    // 下载发货仓库
    public static void downLoadSendWarehouse(final Context context, final Handler handler) {
        DataSupport.deleteAll(WarehouseBean.class, "type=?", "1");
        OkHttpUtils.post().url(AppConfig.URL_WAREHOUSE)
                .addParams("type", "1")
                .addParams(AppConfig.USERNAME, AssistHelper.getUserName())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, String s) {
                String[] rt = s.split(";");
                WarehouseBean bean;
                for (int i = 0; i < rt.length; i++) {
                    Log.i("TAG", "发货仓库 :" + rt[i]);
                    String[] result = rt[i].split(",");
                    bean = new WarehouseBean();
                    bean.setCode(result[0]);//仓库id
                    bean.setWarehouseId(result[1]);//仓库编号
                    bean.setName(result[2]);
                    bean.setType("1");
                    bean.save();
                }
                FLAG++;
                Message message = new Message();
                message.what = FLAG;
                handler.sendMessage(message);
            }
        });

    }

    //下载收货机构
    public static void downLoadReceiveOrgan(final Context context, final Handler handler) {
        DataSupport.deleteAll(OrganBean.class, "type=?", "2");
        OkHttpUtils.post().url(AppConfig.URL_ORGAN)
                .addParams("type", "2")
                .addParams(AppConfig.USERNAME, AssistHelper.getUserName())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, String s) {
                String[] rt = s.split(";");
                OrganBean bean;
                for (int i = 0; i < rt.length; i++) {
                    Log.i("TAG", "收货机构 :" + rt[i]);
                    String[] result = rt[i].split(",");
                    bean = new OrganBean();
                    bean.setCode(result[0]);
                    bean.setName(result[1]);
                    bean.setOrganId(result[0]);
                    bean.setType("2");
                    bean.save();
                }
                FLAG++;
                Message message = new Message();
                message.what = FLAG;
                handler.sendMessage(message);
            }
        });
    }


    //下载收货仓库
    public static void downLoadReceiveWarehouse(final Context context, final Handler handler) {
        DataSupport.deleteAll(WarehouseBean.class, "type=?", "2");
        OkHttpUtils.post().url(AppConfig.URL_WAREHOUSE)
                .addParams("type", "2")
                .addParams(AppConfig.USERNAME, AssistHelper.getUserName())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, String s) {
                String[] rt = s.split(";");
                WarehouseBean bean;
                for (int i = 0; i < rt.length; i++) {
                    Log.i("TAG", "收货仓库 :" + rt[i]);
                    String[] result = rt[i].split(",");
                    bean = new WarehouseBean();
                    bean.setCode(result[0]);
                    bean.setWarehouseId(result[1]);
                    bean.setName(result[2]);
                    bean.setType("2");
                    bean.save();
                }
                FLAG++;
                Message message = new Message();
                message.what = FLAG;
                handler.sendMessage(message);
            }
        });
    }

    //下载产品
    public static void downLoadProduct(final Context context, final Handler handler) {
        DataSupport.deleteAll(ProductBean.class);
        OkHttpUtils.post().url(AppConfig.URL_PRODUCT).addParams("username", AssistHelper.getUserName()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, String s) {
                // Log.i("TAG", s);
                // A2,百速顿200ml*40,;05,舔多收1000ml*12,;03,百速顿500ml*20,;04,百速顿1000ml*12,;08,xhl测试产品,0527001;01,百速顿100ml*40,;
                String[] list = s.split(";");
                for (int i = 0; i < list.length; i++) {
                    String ProductId = list[i].substring(0, 2);
                    String Spec = list[i].substring(0, 2);
                    String name;
                    if (list[i].substring(list[i].length() - 1, list[i].length()).contains(",")) {
                        name = list[i].substring(3, list[i].length() - 1);
                    } else {
                        name = list[i].substring(3);
                    }
                    ProductBean bean = new ProductBean();
                    bean.setSpec(ProductId);
                    bean.setProductId(ProductId);
                    bean.setName(name);
                    bean.save();
                }
                FLAG++;
                Message message = new Message();
                message.what = FLAG;
                handler.sendMessage(message);
            }
        });
    }


}
