package com.bw.expandebale;

import com.bw.expandebale.model.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * DateTime:2020/1/3 0003
 * author:朱贵全(Administrator)
 * function:
 */
public interface Api {
    @GET("small/order/verify/v1/findShoppingCart")
    Observable<Bean>getLeft(@Header("userId") String userId,@Header("sessionId") String sessionId);
}
