package com.yujing.rxjava.constants;

import com.yujing.rxjava.BuildConfig;
/**
 * 常数
 *
 * @author yujing 2019年10月24日13:55:47
 */
public class Constants {
    public static String HOST = BuildConfig.SERVER_URL;
    //https://www.apiopen.top/weatherApi?city=成都
    public static final String WEATHER = "weatherApi";
    //https://www.apiopen.top/getWangYiNews
    public static final String WANG_YI_NEWS = "getWangYiNews";

    public static final String RESPONSE_SUCCESS = "200";//返回码：0

}
