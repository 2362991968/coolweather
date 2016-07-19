package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;

import android.util.Log;

public class HttpUtil {
	/*public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				BufferedReader reader =null;
				URL url;
				try {
					url = new URL(address);
					HttpURLConnection conncetion = (HttpURLConnection) url.openConnection();
					conncetion.setRequestMethod("GET");
					conncetion.setConnectTimeout(8000);
					conncetion.setReadTimeout(8000);
					InputStream in = conncetion.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					if(listener != null){
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
					listener.onError(e);
				}finally{
					if (connection != null) {
						connection.disconnect();
					}
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}*/
	public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(address);
				httpGet.addHeader("Accept_Language", "zh_CN");
				HttpResponse httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == 200 ) {
					HttpEntity entity = httpResponse.getEntity();
					String response = EntityUtils.toString(entity, "utf-8");
					listener.onFinish(response);
				}
			}catch(Exception e){
				listener.onError(e);
				}
			}
				
		}).start();
	}
}
