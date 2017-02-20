package cn.myhug.common.callback;

import java.util.List;

/**
 * Created by zhengxin on 2017/2/9.
 */

public interface IFileUploadCallback {
    void onFileUpload(boolean success,List<String> result);

}
