package com.conductor.shortenurl.controller;

import com.conductor.shortenurl.entity.Response;
import com.conductor.shortenurl.service.UrlService;
import com.conductor.shortenurl.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author renliangyu857
 */
@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    private String host;

    @Value("${server.host}")
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 默认跳转到首页
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }


    /**
     * 生成短链接
     * @param longUrl
     * @param timeout
     * @return
     */
    @GetMapping("/shortUrl/generate")
    @ResponseBody
    public Response generateShortUrl(@RequestParam String longUrl, @RequestParam(required = false) String type, @RequestParam(required = false) Integer timeout) {
        if (UrlUtil.checkURL(longUrl)) {
            if (!longUrl.startsWith("http")) {
                longUrl = "http://" + longUrl;
            }
            String shortURL = urlService.generateShortUrl(longUrl, type, timeout);
            return Response.successs("请求成功", host + shortURL);
        }
        return Response.create(400, "URL有误");
    }

    @GetMapping("/shortUrl/redirect")
    public void redirect(@RequestParam String shortURL, HttpServletResponse response) throws IOException {
        String longURL = urlService.getLongUrlByShortUrl(shortURL);
        if (longURL != null) {
            //查询到对应的原始链接，302重定向
            response.sendRedirect(longURL);
        }
        //没有对应的原始链接，直接返回首页
        response.sendRedirect("/");
    }
}
