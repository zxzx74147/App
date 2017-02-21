package com.zxzx74147.dksq.modules.model;

import com.litesuits.orm.db.annotation.Table;

import cn.myhug.common.base.BaseModel;

/**
 * Created by zhengxin on 2017/2/4.
 */

@Table("action_type")
public class ActionTypeModel extends BaseModel {

    public int id;
    public String name_default;
    public String name_chinese;
    public int default_color;
    public String default_icon;
    public int status;
    public int use_time;
}
