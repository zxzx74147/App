package com.zxzx74147.dksq.modules.model;

import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;
import java.util.List;

import cn.myhug.common.base.BaseModel;

/**
 * Created by zhengxin on 2017/2/4.
 */

@Table("actions")
public class ActionListModel extends BaseModel{

    private List<ActionModel> list = new ArrayList<>(10);
}
