package com.zxzx74147.qiushi.common.data;

import java.io.Serializable;

/**
 * Created by zhengxin on 16/9/5.
 */

public class CommentData implements Serializable{
    /**
     * status : publish
     * user_id : 32044047
     * floor : 4
     * ip : 111.40.2.190
     * created_at : 2016-09-04 13:52:05
     * comment_id : 364537592
     * like_count : 99
     * pos : 0
     * content : 喂，110吗？这家家长不给孩子吃饭。
     * source : android
     * score : null
     * parent_id : 0
     * anonymous : 0
     * neg : 0
     * article_id : 117443359
     * user : {"avatar_updated_at":1472426327,"uid":32044047,"last_visited_at":1466898008,"created_at":1466898008,"state":"active","last_device":"android_10.0.0","role":"n","login":"半夏:","id":32044047,"icon":"2016082823184720.JPEG"}
     */

    public String status;
    public int user_id;
    public int floor;
    public String ip;
    public String created_at;
    public int comment_id;
    public int like_count;
    public int pos;
    public String content;
    public String source;
    public Object score;
    public int parent_id;
    public int anonymous;
    public int neg;
    public int article_id;
    /**
     * avatar_updated_at : 1472426327
     * uid : 32044047
     * last_visited_at : 1466898008
     * created_at : 1466898008
     * state : active
     * last_device : android_10.0.0
     * role : n
     * login : 半夏:
     * id : 32044047
     * icon : 2016082823184720.JPEG
     */

    public UserData user;


}
