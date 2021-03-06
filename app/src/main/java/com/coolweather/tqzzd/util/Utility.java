package com.coolweather.tqzzd.util;

import com.coolweather.tqzzd.db.City;
import com.coolweather.tqzzd.db.County;
import com.coolweather.tqzzd.db.Province;
import com.coolweather.tqzzd.gson.Weather;
import com.google.gson.Gson;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wonder on 17-3-28.
 */

public class Utility {

    /*解析和处理服务器返回的省级数据*/
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allPrivinces = new JSONArray(response);
                for(int i = 0;i<allPrivinces.length();i++){
                    JSONObject provinceObject = allPrivinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /*解析和处理服务器返回的市级数据*/
    public static boolean handleCityResponse(String response,int privinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for(int i = 0;i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(privinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for(int i = 0;i<allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    //将返回的JSON数据解析成Weather实体类
    public static Weather handleWeatherResponse(String response){
        try{
            JSONObject jsonObject= new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
