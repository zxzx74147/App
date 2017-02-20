package com.zxzx74147.dksq.modules.action.service;

import android.content.Context;

import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.dksq.http.Config;
import com.zxzx74147.dksq.modules.action.config.ActionHttpConfig;
import com.zxzx74147.dksq.modules.model.ActionListModel;
import com.zxzx74147.dksq.modules.model.ActionModel;

import cn.myhug.common.base.BaseDataService;
import cn.myhug.common.http.CommonHttpRequest;

import static android.R.attr.id;

/**
 * Created by zhengxin on 2017/2/17.
 */

public class ActionDataService extends BaseDataService {


    public ActionDataService(Context context) {
        super(context);
    }

    public void loadActionModel(int id, ZXHttpCallback<ActionModel> callback) {
        CommonHttpRequest<ActionModel> request = (CommonHttpRequest<ActionModel>) getRequest(ActionModel.class);
        request.setUrl(Config.HOST + ActionHttpConfig.ADDRESSS_ACTION_DETAIL);
        request.addParam("id", id);

        request.send(callback);
    }

    public void loadActionListModel(String key, ZXHttpCallback<ActionListModel> callback) {
        CommonHttpRequest<ActionListModel> request = (CommonHttpRequest<ActionListModel>) getRequest(ActionListModel.class);
        request.setUrl(Config.HOST + ActionHttpConfig.ADDRESSS_ACTION_LIST);
        request.addParam("page_key", key);
        request.send(callback);
    }

    public void addActionModel(ActionModel model, ZXHttpCallback<ActionModel> callback) {
        CommonHttpRequest<ActionModel> request = (CommonHttpRequest<ActionModel>) getRequest(ActionModel.class);
        request.setUrl(Config.HOST + ActionHttpConfig.ADDRESSS_ACTION_ADD);
        request.addParam("comment", model.comment);
        request.addParam("color", model.color);
        request.addParam("type", model.type);
        request.addParam("image", model.image);
        request.send(callback);
    }

    public void editActionModel(ActionModel model, ZXHttpCallback<ActionModel> callback) {
        CommonHttpRequest<ActionModel> request = (CommonHttpRequest<ActionModel>) getRequest(ActionModel.class);
        request.setUrl(Config.HOST + ActionHttpConfig.ADDRESSS_ACTION_EDIT);
        request.addParam("id", id);
        request.addParam("comment", model.comment);
        request.addParam("color", model.color);
        request.addParam("type", model.type);
        request.addParam("image", model.image);
        request.send(callback);
    }

    public void delActionModel(ActionModel model, ZXHttpCallback<ActionModel> callback) {
        CommonHttpRequest<ActionModel> request = (CommonHttpRequest<ActionModel>) getRequest(ActionModel.class);
        request.setUrl(Config.HOST + ActionHttpConfig.ADDRESSS_ACTION_DEL);
        request.addParam("id", id);
        request.send(callback);
    }

    public void finishActionModel(ActionModel model, ZXHttpCallback<ActionModel> callback) {
        CommonHttpRequest<ActionModel> request = (CommonHttpRequest<ActionModel>) getRequest(ActionModel.class);
        request.setUrl(Config.HOST + ActionHttpConfig.ADDRESSS_ACTION_FINISH);
        request.addParam("id", id);
        request.send(callback);
    }
}
