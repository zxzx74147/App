package com.zxzx74147.qiushi.common.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengxin on 16/8/21.
 */
public class ItemData implements Serializable{
    public String format;
    public String image;
    public int published_at;
    public String tag;
    /**
     * avatar_updated_at : 1393745761
     * uid : 14406510
     * last_visited_at : 1393474120
     * created_at : 1393474120
     * state : active
     * last_device : android_2.7.1
     * role : n
     * login : 蓝色oliver
     * id : 14406510
     * icon : 20140302153512.jpg
     */

    public UserData user;
    public ImageSizeData image_size;
    public int id;
    /**
     * down : -38
     * up : 1175
     */

    public VotesData votes;
    public int created_at;
    public String content;
    public String state;
    public int comments_count;
    public boolean allow_comment;
    public int share_count;
    public String type;

    public CommentData hot_comment;

    /**
     * high_url : http://qiubai-video.qiushibaike.com/UKFNXUGOUAMERKWD.mp4
     * pic_size : [480,480]
     * pic_url : http://qiubai-video.qiushibaike.com/UKFNXUGOUAMERKWD.jpg
     * low_url : http://qiubai-video.qiushibaike.com/UKFNXUGOUAMERKWD_3g.mp4
     * loop : 117009
     */

    public String high_url;
    public String pic_url;
    public String low_url;
    public int loop;
    public List<Integer> pic_size;
}
