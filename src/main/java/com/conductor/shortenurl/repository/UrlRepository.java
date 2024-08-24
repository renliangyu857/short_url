package com.conductor.shortenurl.repository;

import com.conductor.shortenurl.entity.UrlMapping;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author renliangyu857
 */
@Repository
@Mapper
public interface UrlRepository {

  void saveUrlMapping(UrlMapping urlMapping);

  UrlMapping getLongUrlByShortUrl(String shortUrl);

  void deleteUrlMapping(String shortUrl);

  void deleteExpiredUrlMappings();
}
