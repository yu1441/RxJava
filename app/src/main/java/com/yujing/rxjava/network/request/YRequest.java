package com.yujing.rxjava.network.request;

import com.yujing.rxjava.constants.Constants;
import com.yujing.rxjava.model.WangYiNews;
import com.yujing.rxjava.model.Weather;
import com.yujing.rxjava.network.response.YResponse;
import com.yujing.rxjava.network.response.YResponseNews;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * 封装请求的接口
 */
public interface YRequest {
    //https://www.apiopen.top/weatherApi?city=成都
    @GET(Constants.WEATHER)
    Call<YResponse<Weather>> getCall(@Query("city") String city);

    @GET(Constants.WEATHER)
    Observable<YResponse<Weather>> get(@Query("city") String city);

    @GET("weatherApi?city=成都")
    Observable<YResponse<Weather>> get2();

    //https://api.apiopen.top/getWangYiNews
    @GET(Constants.WANG_YI_NEWS)
    Observable<ResponseBody> newsResponseBody();

    //https://api.apiopen.top/getWangYiNews
    @GET(Constants.WANG_YI_NEWS)
    Observable<YResponseNews<WangYiNews>> news();
}
