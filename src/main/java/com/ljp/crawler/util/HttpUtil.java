package com.ljp.crawler.util;

import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


/**
 * @author ：liujp
 * @date ：Created in 2020/3/22 11:34
 * @description：
 * @modified By：
 * @version: $
 */

public class HttpUtil {

    private String name;
    private static HttpURLConnection connection = null;

    public static String getHtml(String url) {
        String content = "";
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream in = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, "utf-8");
                BufferedReader reader = new BufferedReader(isr);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content += line;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return content;
    }
}
