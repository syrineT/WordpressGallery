package com.syrine.com.syrine.api;

import retrofit.RestAdapter;

/**
 * Created by syrinetrabelsi on 24/01/15.
 */
public class RetrofitService {
    public static IWordpressService getService() {


        return new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint("https://public-api.wordpress.com/").build().create(IWordpressService.class);
    }
}
