package com.zxzx74147.dksq.modules.data;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.annotation.Unique;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by zhengxin on 2017/2/4.
 */

@Table("actions")
public class ActionData {

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

    public String image;

    @Default("0")
    @NotNull
    public int start_time;

    @Default("0")
    @NotNull
    public int end_time;
}
