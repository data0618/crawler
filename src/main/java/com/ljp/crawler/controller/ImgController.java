package com.ljp.crawler.controller;

import com.ljp.crawler.constants.ImpConstants;
import com.ljp.crawler.constants.UserAgentConstant;
import com.ljp.crawler.pipeline.img.MeiZiTuImgPipeline;
import com.ljp.crawler.pipeline.img.ZhiHuImgPipeline;
import com.ljp.crawler.processor.imgprocessor.MeiZiTuImgProcessor;
import com.ljp.crawler.processor.imgprocessor.ZhiHuImgProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：liujp
 * @date ：Created in 2020/3/20 23:19
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
@RestController
public class ImgController {

    @Autowired
    MeiZiTuImgProcessor imgProcessor;
    @Autowired
    ZhiHuImgProcessor zhiHuImgProcessor;
    @Autowired
    private MeiZiTuImgPipeline meiZiTuImgPipeline;
    @Autowired
    private ZhiHuImgPipeline zhiHuImgPipeline;

    @RequestMapping(
            value = {ImpConstants.MEIZITU},
            method = {RequestMethod.GET}
    )
    public String sisterImg(HttpServletRequest request) throws Exception {
        String url = request.getQueryString().replace("url=","");
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("101.101.101.101",8888,"username","password")));
        Spider.create(imgProcessor)
                //从"https://github.com/code4craft"开始抓
                //.addUrl("https://www.tooopen.com/img/88_879_1_2.aspx")
                .addUrl(url)
                .addPipeline(meiZiTuImgPipeline)
                //.setDownloader(httpClientDownloader)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
        return "下载完成";
    }

    @RequestMapping(
            value = {ImpConstants.ZHIHU},
            method = {RequestMethod.GET}
    )
    public String zhiHuImg(HttpServletRequest request) throws Exception {
        String url = request.getQueryString().replace("url=","");
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("49.74.64.50",8080,"1234","1234")));
        Spider.create(zhiHuImgProcessor)
                //从"https://github.com/code4craft"开始抓
                //.addUrl("https://www.tooopen.com/img/88_879_1_2.aspx")
                .addUrl(url)
                .addPipeline(zhiHuImgPipeline)
                //.setDownloader(httpClientDownloader)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
        return "下载完成";
    }


}
