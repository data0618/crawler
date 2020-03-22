package com.ljp.crawler.processor.imgprocessor;

import com.ljp.crawler.constants.UserAgentConstant;
import com.ljp.crawler.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：liujp
 * @date ：Created in 2020/3/20 23:30
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@Slf4j
public class ZhiHuImgProcessor implements PageProcessor {

    /**
     * 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private Site site = Site.me().setCycleRetryTimes(1).setRetryTimes(1).setSleepTime(200).setTimeOut(3 * 1000)
            .setUserAgent(UserAgentConstant.Pc.FIRE_FOX_MAC)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3").setCharset("UTF-8");

    @Override
    public void process(Page page) {
       // page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
       // page.putField("name", page.getHtml().xpath("//a[@class='pic']/img").toString());
       // page.getUrl().regex("https://voice\\.hupu\\.com/nba/[0-9]{7}\\.html").match();
//        page.putField("name", page.getHtml().xpath("/html/body/div/ul/li/div/a/img/@src"));


        final String html = HttpUtil.getHtml(page.getRequest().getUrl());
        //final Document document = Jsoup.parse(html);
        final Html html1 = Html.create(html);
        page.putField("img", html1.xpath("img/@src"));
        /*if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));*/

        // 部分三：从页面发现后续的url地址来抓取
       // page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        //page.addTargetRequests(page.getHtml().links().regex("(https://www.mzitu.com/\\d+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

}
