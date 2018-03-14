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

    public static void downLoadSendOrgan(final Context context, final Handler handler) {
        DataSupport.deleteAll(OrganBean.class, "type=?", "1");
        OrganBean bean = new OrganBean();
        bean.setCode("0001");
        bean.setName("发货机构");
        bean.setOrganId("1111111111");
        bean.setType("1");
        bean.save();

        OrganBean bean2 = new OrganBean();
        bean2.setCode("0002");
        bean2.setName("发货机构002");
        bean.setOrganId("2222222222");
        bean2.setType("1");
        bean2.save();

        FLAG++;

        Message message = new Message();
        message.what = FLAG;
        handler.sendMessage(message);

    }

    //下载发货仓库
    public static void downLoadSendWarehouse(final Context context, final Handler handler) {
        DataSupport.deleteAll(WarehouseBean.class, "type=?", "1");
        WarehouseBean bean = new WarehouseBean();
        bean.setName("收货仓库01");
        bean.setCode("0001");
        bean.setWarehouseId("333333344");
        bean.setType("1");
        bean.save();
        FLAG++;

        Message message = new Message();
        message.what = FLAG;
        handler.sendMessage(message);
    }

    public static void downLoadReceiveOrgan(final Context context, final Handler handler) {
        DataSupport.deleteAll(OrganBean.class, "type=?", "2");
        OrganBean bean = new OrganBean();
        bean.setCode("0008");
        bean.setName("收货机构**");
        bean.setOrganId("44444444444");
        bean.setType("2");
        bean.save();

        FLAG++;

        Message message = new Message();
        message.what = FLAG;
        handler.sendMessage(message);

    }

    //下载发货仓库
    public static void downLoadReceiveWarehouse(final Context context, final Handler handler) {
        DataSupport.deleteAll(WarehouseBean.class, "type=?", "2");
        WarehouseBean bean = new WarehouseBean();
        bean.setName("收货仓库0008");
        bean.setCode("0008");
        bean.setWarehouseId("3333333333");
        bean.setType("2");
        bean.save();
        FLAG++;

        Message message = new Message();
        message.what = FLAG;
        handler.sendMessage(message);
    }

    //下载产品
//    public static void downLoadProduct(final Context context, final Handler handler) {
//        DataSupport.deleteAll(ProductBean.class);
//        ProductBean bean = new ProductBean();
//        bean.setSpec("555");
//        bean.setProductId("88888888");
//        bean.setName("永农产品");
//        bean.save();
//
//        FLAG++;
//
//        Message message = new Message();
//        message.what = FLAG;
//        handler.sendMessage(message);
//    }


    //**********************************************************
//    //下载发货机构
//    public static void downLoadSendOrgan(final Context context, final Handler handler) {
//        DataSupport.deleteAll(OrganBean.class, "type=?", "1");
//        OkHttpUtils.post().url("")
//                .addParams("type", "1")
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(Call call, String s) {
//                try {
//                    JSONObject ob = new JSONObject(s);
//                    String code = ob.getString("returnCode");
//                    if ("0".equals(code)) {
//                        JSONArray array = ob.getJSONArray("returnData");
//                        OrganBean bean;
//                        for (int i = 0; i < array.length(); i++) {
//                            JSONObject obs = (JSONObject) array.opt(i);
//                            bean = new OrganBean();
////                                  "compantFullName": "公司名称",
////                                   "encode": "20160927",
////                                   "code": "5516ec3d-803a-4cc6-9415-9d99cc26fd3b
//                            bean.setEncode(obs.getString("encode"));
//                            bean.setCode(obs.getString("code"));
//                            bean.setName(obs.getString("compantFullName"));
//                            bean.setType("1");
//                            bean.save();
//                        }
//                        FLAG++;
//
//                        Message message = new Message();
//                        message.what = FLAG;
//                        handler.sendMessage(message);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    //下载收货机构
//    public static void downLoadReceiveOrgan(final Context context, final Handler handler) {
//        DataSupport.deleteAll(OrganBean.class, "type=?", "2");
//        OkHttpUtils.post().url("")
//                .addParams("type", "2")
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(Call call, String s) {
//                try {
//                    JSONObject ob = new JSONObject(s);
//                    String code = ob.getString("returnCode");
//                    if ("0".equals(code)) {
//                        JSONArray array = ob.getJSONArray("returnData");
//                        OrganBean bean;
//                        for (int i = 0; i < array.length(); i++) {
//                            JSONObject obs = (JSONObject) array.opt(i);
//                            bean = new OrganBean();
////                                  "compantFullName": "公司名称",
////                                   "encode": "20160927",
////                                   "code": "5516ec3d-803a-4cc6-9415-9d99cc26fd3b
//                            bean.setEncode(obs.getString("encode"));
//                            bean.setCode(obs.getString("code"));
//                            bean.setName(obs.getString("compantFullName"));
//                            bean.setType("2");
//                            bean.save();
//                        }
//                        FLAG++;
//
//                        Message message = new Message();
//                        message.what = FLAG;
//                        handler.sendMessage(message);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    //下载发货仓库
//    public static void downLoadSendWarehouse(final Context context, final Handler handler) {
//        DataSupport.deleteAll(WarehouseBean.class, "type=?", "1");
//        OkHttpUtils.post().url("")
//                .addParams("type", "1").build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onResponse(Call call, String s) {
//                try {
//                    JSONObject ob = new JSONObject(s);
//                    String code = ob.getString("returnCode");
//                    if ("0".equals(code)) {
//                        JSONArray array = ob.getJSONArray("returnData");
//                        WarehouseBean bean;
//                        for (int i = 0; i < array.length(); i++) {
//                            JSONObject obs = (JSONObject) array.opt(i);
//                            bean = new WarehouseBean();
////                                    "enCode": "123456",
////                                            "name": "aaaa默认仓库",
////                                            "code": "31a1cc84-62b2-4103-87f3-04e2cb2b2237",
////                                            "companyCode": "bedf20c3-fbfe-4f52-95de-2b080caa0bc2"
//                            bean.setName(obs.getString("name"));
//                            bean.setCompanyCode(obs.getString("companyCode"));
//                            bean.setCode(obs.getString("code"));
//                            bean.setEnCode(obs.getString("enCode"));
//                            bean.setType("1");
//                            bean.save();
//                        }
//                        FLAG++;
//
//                        Message message = new Message();
//                        message.what = FLAG;
//                        handler.sendMessage(message);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//    //下载发货仓库
//    public static void downLoadReceiveWarehouse(final Context context, final Handler handler) {
//        DataSupport.deleteAll(WarehouseBean.class, "type=?", "2");
//        OkHttpUtils.post().url("")
//                .addParams("type", "2").build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//                Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onResponse(Call call, String s) {
//                try {
//                    JSONObject ob = new JSONObject(s);
//                    String code = ob.getString("returnCode");
//                    if ("0".equals(code)) {
//                        JSONArray array = ob.getJSONArray("returnData");
//                        WarehouseBean bean;
//                        for (int i = 0; i < array.length(); i++) {
//                            JSONObject obs = (JSONObject) array.opt(i);
//                            bean = new WarehouseBean();
////                                    "enCode": "123456",
////                                            "name": "aaaa默认仓库",
////                                            "code": "31a1cc84-62b2-4103-87f3-04e2cb2b2237",
////                                            "companyCode": "bedf20c3-fbfe-4f52-95de-2b080caa0bc2"
//                            bean.setName(obs.getString("name"));
//                            bean.setCompanyCode(obs.getString("companyCode"));
//                            bean.setCode(obs.getString("code"));
//                            bean.setEnCode(obs.getString("enCode"));
//                            bean.setType("2");
//                            bean.save();
//                        }
//                        FLAG++;
//
//                        Message message = new Message();
//                        message.what = FLAG;
//                        handler.sendMessage(message);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
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
                String[] list=s.split(";");
                for (int i = 0; i < list.length; i++) {
                    String ProductId= list[i].substring(0,2);
                    String Spec= list[i].substring(0,2);
                    String name;
                    if (list[i].substring(list[i].length()-1,list[i].length()).contains(",")) {
                        name=list[i].substring(3,list[i].length()-1);
                    }else {
                        name=list[i].substring(3);
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
