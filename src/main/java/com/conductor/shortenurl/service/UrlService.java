package com.conductor.shortenurl.service;

/**
 * @author renliangyu857
 */
public interface UrlService {

  /**
   * 根据长URL生成短URL(不带过期时间)
   *
   * @param longUrl 长Url
   * @param timeout 过期时间
   * @return 短Url
   */
  String generateShortUrl(String longUrl, String type, Integer timeout);


  /**
   * 根据短URL返回长URL
   *
   * @param shortUrl 短Url
   * @return 长URL
   */
  String getLongUrlByShortUrl(String shortUrl);

}
