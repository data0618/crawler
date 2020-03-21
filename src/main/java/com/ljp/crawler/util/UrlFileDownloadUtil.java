package com.ljp.crawler.util;

/**
 * @author ：liujp
 * @date ：Created in 2020/3/21 14:41
 * @description：
 * @modified By：
 * @version: $
 */
import com.ljp.crawler.constants.UserAgentConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.net.*;
import java.util.List;

@Slf4j
@Component
public class UrlFileDownloadUtil {


    @Value("${file.savePath}")
    private String savePath;

    /**
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地
     */
    public void saveFiles(List<String> urlList, List<String> names) {

        for (int i = 0; i < urlList.size(); i++) {
            saveFile(urlList.get(i), names.get(i));
        }
    }

    public void saveFiles(List<String> urlList) {

        for (int i = 0; i < urlList.size(); i++) {
            saveFile(urlList.get(i));
        }
    }

    /**
     * 下载一张图片
     * @param u
     */
    public void saveFile(String u) {

        String[] files = u.split("/");
        String name = files[files.length - 1];
        saveFile(u, name);

    }

    public void saveFile(String u, String name){
        URL url = null;
        try {
            url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Referer", url.getHost());
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", UserAgentConstant.Pc.FIRE_FOX_WINDOWS1);
            DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath + name));

            byte[] buffer = new byte[1024 * 50];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            log.info("已经下载：" + savePath + name);
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
