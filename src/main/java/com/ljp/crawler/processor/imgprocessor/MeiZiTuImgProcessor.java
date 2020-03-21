package com.ljp.crawler.processor.imgprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author ：liujp
 * @date ：Created in 2020/3/20 23:30
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@Slf4j
public class MeiZiTuImgProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);


    @Override
    public void process(Page page) {
       // page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
       // page.putField("name", page.getHtml().xpath("//a[@class='pic']/img").toString());
       // page.getUrl().regex("https://voice\\.hupu\\.com/nba/[0-9]{7}\\.html").match();
//        page.putField("name", page.getHtml().xpath("/html/body/div/ul/li/div/a/img/@src"));
        page.putField("img", page.getHtml().xpath("img/@data-original"));

        /*if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));*/

        // 部分三：从页面发现后续的url地址来抓取
       // page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

}