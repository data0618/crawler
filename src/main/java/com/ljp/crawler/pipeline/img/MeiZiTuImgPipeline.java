package com.ljp.crawler.pipeline.img;

import com.ljp.crawler.util.UrlFileDownloadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.PlainText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：liujp
 * @date ：Created in 2020/3/21 10:08
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
public class MeiZiTuImgPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> mapResults = resultItems.getAll();
        final Object img = mapResults.get("img");

        if (img instanceof PlainText){
            final List<String> all = ((PlainText) img).all();
            new UrlFileDownloadUtil().downloadPictures(all);
        }


    }
}