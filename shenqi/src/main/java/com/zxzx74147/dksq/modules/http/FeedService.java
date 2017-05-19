package com.zxzx74147.dksq.modules.http;

import com.zxzx74147.dksq.modules.model.ActionListModel;
import com.zxzx74147.dksq.modules.model.ItemListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhengxin on 2017/3/27.
 */

public interface FeedService {
    @GET("list/{count}")
    Call<ItemListModel> listRepos(@Path("count") String count);
}
