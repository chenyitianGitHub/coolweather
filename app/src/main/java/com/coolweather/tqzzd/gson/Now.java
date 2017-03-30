package com.coolweather.tqzzd.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonder on 17-3-29.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }

}
