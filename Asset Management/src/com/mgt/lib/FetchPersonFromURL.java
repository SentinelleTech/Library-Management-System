package com.mgt.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class FetchPersonFromURL {
	
	private static HttpURLConnection connection;
	BufferedReader br;
	String str;
	StringBuffer sb;

	public FetchPersonFromURL() {
		
		
				sb = new StringBuffer();
				
				try {
					
					URL url = new URL("http://localhost/Institution%20Library/member.php");
					
					connection = (HttpURLConnection) url.openConnection();
					
					//Request setup
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					
					int status = connection.getResponseCode();
					//System.out.println(status);
					
					if(status > 299){
						
						br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
						
						while((str = br.readLine()) != null){
							sb.append(str);
						}
						br.close();
						
					}else{
						
						br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						
						while((str = br.readLine()) != null){
							sb.append(str);
						}
						br.close();
					}
					parse(sb.toString());
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					connection.disconnect();
				}
	}
	
			public static String parse(String responseBody){
				JSONArray mtu = new JSONArray(responseBody);
				
				for(int i = 0; i < mtu.length(); i++){
					
					JSONObject alb = mtu.getJSONObject(i);
					
					int id = alb.getInt("Borrower_Id");
					String name = alb.getString("Borrower_Name");
					String des = alb.getString("Designation");
					
					System.out.println(id + " : "  + name + " : "  + des);
				}
				
				return null;
			}

	public static void main(String[] args) {
		new FetchPersonFromURL();

	}

}
