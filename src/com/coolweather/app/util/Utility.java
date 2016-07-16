package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.Country;
import com.coolweather.app.model.Province;

public class Utility {
	
	/**
	 * parse province data from string
	 * @param coolWeatherDB
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response){
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * parse and resolve citydata from server
	 * @param coolWeatherDB
	 * @param response
	 * @param provinceId
	 * @return
	 */
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId){
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String array[] = c.split("\\|");
					City city = new City();
					city.setProvinceId(provinceId);
					city.setCityName(array[1]);
					city.setCityCode(array[0]);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * parse and resolve country data from server
	 * @param coolWeatherDB
	 * @param response
	 * @param cityId
	 * @return
	 */
	public static boolean handleCountriesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId){
		if (!TextUtils.isEmpty(response)) {
			String[] allCountries = response.split(",");
			if (allCountries != null && allCountries.length >0) {
				for (String c : allCountries) {
					String[] array = c.split("\\|");
					Country country = new Country();
					country.setCountryCode(array[0]);
					country.setCountryName(array[1]);
					country.setCityId(cityId);
					coolWeatherDB.saveCountry(country);
				}
				return true;
			}
		}
		return false;
	}
}
