package com.bw.week2_one;

import com.bw.week2_one.model.bean.LeftBean;
import com.bw.week2_one.model.bean.RightBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public interface Api {
    @GET("small/commodity/v1/findCategory")
    Observable<LeftBean>getLeft();
    @GET("small/commodity/v1/findCommodityByCategory")
    Observable<RightBean>getRight(@Query("categoryId") String categoryId, @Query("page")int page, @Query("count")int count);
}
