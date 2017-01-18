package com.zxzx74147.dksq.modules.data;

import java.util.List;

/**
 * Created by zhengxin on 2017/1/10.
 */

public class ItemData {


    public int id;
    public int create_time;
    public String content;
    public String format;
    public String image;
    public String image_low;

    public String image_high;
    public ImageSizeData image_size;
    public String video;
    public String video_low;
    public String video_pic;
    public Object video_pic_size;
    public int video_loop;
    public int share_count;
    public int vote_up;
    public int vote_down;
    public String status;
    public int commont_count;
    public String commont_hot;
    public String type;
    public UserData user;

    public static class ImageSizeData {
        public List<Integer> s;
        public List<Integer> m;
    }

}
