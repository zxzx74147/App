package com.zxzx74147.qiushi.common.http;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhengxin on 16/9/5.
 */

public class QiushiReadList {
    private static List<Integer> mReadList = new LinkedList<>();
    private static StringBuffer sb = new StringBuffer(mReadList.size()*15);
    public static void addList(int id){
        if(mReadList.size()>20){
            mReadList.remove(0);
        }
        if(mReadList.contains(id)){
            return;
        }
        mReadList.add(id);
    }
    public  static String getList(){
        sb.setLength(0);
        sb.append("[");
        for(Integer id:mReadList){
            sb.append(String.valueOf(id));
            sb.append(",");
        }
        if(sb.length()>1) {
            sb.setCharAt(sb.length() - 1, ']');
        }else{
            return null;
        }
        mReadList.clear();
        return sb.toString();
    }
}
