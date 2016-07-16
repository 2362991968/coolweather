package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.xml.sax.InputSource;

public class HttpUtil {
	public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				URL url;
				try {
					url = new URL(address);
					HttpURLConnection conncetion = (HttpURLConnection) url.openConnection();
					conncetion.setRequestMethod("GET");
					conncetion.setConnectTimeout(8000);
					conncetion.setReadTimeout(8000);
					InputStream in = conncetion.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					if(listener != null){
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					listener.onError(e);
				}finally{
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}).start();
	}
}
