package com.example.PreServe.distance;

import org.springframework.http.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class DistanceCal {

    public static String getApiByAddress() {
        String appkey = "KakaoAK 2ecf5febe1e05d5d376370e2b4d6c779";
        String city = "서울특별시 구로구 천왕로21";
        String encode = null;
        try {
            encode = URLEncoder.encode(city,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        try {
            String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query="+encode;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization",appkey);
            connection.setDoOutput(true);

            //응답결과
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();


            return response.toString();
        } catch (Exception e) {
            return "failed to get response";
        }
    }

    public static String serachByCustomerLoc() {
        String appkey = "KakaoAK 2ecf5febe1e05d5d376370e2b4d6c779";
        String encode = null;
        String keyword = "쌀국수";
        String x="126.838929498352";
        String y="37.4791754743291";

        try {
            encode = URLEncoder.encode(keyword,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        try {
            String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?y="+y+"&x="+x+"&radius=20000&query="+encode;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization",appkey);
            connection.setDoOutput(true);

            //응답결과
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();


            return response.toString();
        } catch (Exception e) {
            return "failed to get response";
        }

    }


}
