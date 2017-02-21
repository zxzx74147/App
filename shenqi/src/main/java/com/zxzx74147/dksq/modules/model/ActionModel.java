package com.zxzx74147.dksq.modules.model;

import android.databinding.Bindable;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.annotation.Unique;
import com.litesuits.orm.db.enums.AssignType;

import java.util.LinkedList;
import java.util.List;

import cn.myhug.common.base.BaseModel;

/**
 * Created by zhengxin on 2017/2/4.
 */

@Table("action")
public class ActionModel extends BaseModel {

    @Column("id")
    @PrimaryKey(AssignType.BY_MYSELF)
    @Unique
    public long id;

    @Default("0")
    @NotNull
    public int type;

    @Default("0")
    @NotNull
    public int color;

    @Default("0")
    @NotNull
    public int status;

    public String comment;

    @Bindable
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> image = new LinkedList<>();

    @Bindable
    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> local_image = new LinkedList<>();

    @Bindable
    public List<String> getLocalImage() {
        return local_image;
    }

    public void setLocalImage(List<String> local_image) {
        this.local_image = local_image;
    }

    @Default("0")
    @NotNull
    public int start_time;

    @Default("0")
    @NotNull
    public int end_time;

    @Default("0")
    @NotNull
    public int duration_expect;

    @Default("0")
    @NotNull
    public int duration_actual;

    @Default("0")
    @NotNull
    public int submit_status;

    public int getImageCount(){
        return image.size()+local_image.size();
    }

    public String getImage(int index){
        int size1 = image.size();
        int size2 = local_image.size();
        if(index<size1){
            return image.get(index);
        }
        if(index<size1+size2){
            return local_image.get(index-size1);
        }
        return null;
    }
}
