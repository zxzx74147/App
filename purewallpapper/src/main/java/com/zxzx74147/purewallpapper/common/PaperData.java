package com.zxzx74147.purewallpapper.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengxin on 16/9/13.
 */

public class PaperData implements Serializable {

    /**
     * enterable : true
     * file_id : 13477414
     * size_id : 0
     * group_id : 284861
     * key : 284861-102
     * number : 1284861
     * dlview : http://api.lovebizhi.com/android_v3.php?a=dlview&group_id=284861&user_id=0&client_id=1001&model_id=102&size_id=0
     * detail : http://api.lovebizhi.com/android_v3.php?a=detail&group_id=284861&spdy=1&device=google%28Nexus+6%29&uuid=f76d92694bddb46609f1d195b01496da&mode=1&client_id=1001&device_id=67662286&model_id=102&size_id=0&channel_id=1&screen_width=1440&screen_height=2560&bizhi_width=2880&bizhi_height=2560&version_code=80&language=zh-CN&mac=&original=0
     * image : {"small":"http://s.qdcdn.com/cl/13477414,480,426.webp","big":"http://s.qdcdn.com/cl/13477414,1440,1280.webp","original":"http://s.qdcdn.com/c/13477414,2880,2560.webp","vip_original":"http://s.qdcdn.com/c/13477414,2880,2560.jpg","diy":"http://s.qdcdn.com/d/13477414.jpg"}
     * counts : {"loved":"541","share":"0","down":"1.1万","puzzle":"5"}
     * share : {"api":"http://api.lovebizhi.com/user.php?a=share&group_id=284861&client_id=1001&version_code=80","url":"http://m.lovebizhi.com/share/1284861?from=client_1001","pic":"http://s.qdcdn.com/f/13477414.jpg"}
     * user : {"love":{"status":false,"create":"http://api.lovebizhi.com/user.php?a=love&op=create&client_id=1001&version_code=80","remove":"http://api.lovebizhi.com/user.php?a=love&op=remove&client_id=1001&version_code=80"},"addtag":"http://api.lovebizhi.com/user.php?a=addtag&group_id=284861&client_id=1001&version_code=80"}
     * tags : [{"tid":110809,"name":"偶像来了卡通版","url":"http://api.lovebizhi.com/android_v3.php?a=tag&id=110809&name=%E5%81%B6%E5%83%8F%E6%9D%A5%E4%BA%86%E5%8D%A1%E9%80%9A%E7%89%88&spdy=1&device=google%28Nexus+6%29&uuid=f76d92694bddb46609f1d195b01496da&mode=1&client_id=1001&device_id=67662286&model_id=102&size_id=0&channel_id=1&screen_width=1440&screen_height=2560&bizhi_width=2880&bizhi_height=2560&version_code=80&language=zh-CN&mac=&original=0"},{"tid":38008,"name":"林青霞","url":"http://api.lovebizhi.com/android_v3.php?a=tag&id=38008&name=%E6%9E%97%E9%9D%92%E9%9C%9E&spdy=1&device=google%28Nexus+6%29&uuid=f76d92694bddb46609f1d195b01496da&mode=1&client_id=1001&device_id=67662286&model_id=102&size_id=0&channel_id=1&screen_width=1440&screen_height=2560&bizhi_width=2880&bizhi_height=2560&version_code=80&language=zh-CN&mac=&original=0"},{"tid":108381,"name":"偶像来了","url":"http://api.lovebizhi.com/android_v3.php?a=tag&id=108381&name=%E5%81%B6%E5%83%8F%E6%9D%A5%E4%BA%86&spdy=1&device=google%28Nexus+6%29&uuid=f76d92694bddb46609f1d195b01496da&mode=1&client_id=1001&device_id=67662286&model_id=102&size_id=0&channel_id=1&screen_width=1440&screen_height=2560&bizhi_width=2880&bizhi_height=2560&version_code=80&language=zh-CN&mac=&original=0"}]
     * allow_diy : true
     */

    public boolean enterable;
    public int file_id;
    public int size_id;
    public int group_id;
    public String key;
    public String number;
    public String dlview;
    public String detail;
    /**
     * small : http://s.qdcdn.com/cl/13477414,480,426.webp
     * big : http://s.qdcdn.com/cl/13477414,1440,1280.webp
     * original : http://s.qdcdn.com/c/13477414,2880,2560.webp
     * vip_original : http://s.qdcdn.com/c/13477414,2880,2560.jpg
     * diy : http://s.qdcdn.com/d/13477414.jpg
     */

    public ImageData image;
    /**
     * loved : 541
     * share : 0
     * down : 1.1万
     * puzzle : 5
     */

    public CountsData counts;
    /**
     * api : http://api.lovebizhi.com/user.php?a=share&group_id=284861&client_id=1001&version_code=80
     * url : http://m.lovebizhi.com/share/1284861?from=client_1001
     * pic : http://s.qdcdn.com/f/13477414.jpg
     */

    public ShareData share;
    /**
     * love : {"status":false,"create":"http://api.lovebizhi.com/user.php?a=love&op=create&client_id=1001&version_code=80","remove":"http://api.lovebizhi.com/user.php?a=love&op=remove&client_id=1001&version_code=80"}
     * addtag : http://api.lovebizhi.com/user.php?a=addtag&group_id=284861&client_id=1001&version_code=80
     */

    public UserData user;
    public boolean allow_diy;
    /**
     * tid : 110809
     * name : 偶像来了卡通版
     * url : http://api.lovebizhi.com/android_v3.php?a=tag&id=110809&name=%E5%81%B6%E5%83%8F%E6%9D%A5%E4%BA%86%E5%8D%A1%E9%80%9A%E7%89%88&spdy=1&device=google%28Nexus+6%29&uuid=f76d92694bddb46609f1d195b01496da&mode=1&client_id=1001&device_id=67662286&model_id=102&size_id=0&channel_id=1&screen_width=1440&screen_height=2560&bizhi_width=2880&bizhi_height=2560&version_code=80&language=zh-CN&mac=&original=0
     */

    public List<TagsData> tags;

}
