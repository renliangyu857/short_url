package com.conductor.shortenurl.entity;

import java.util.Date;

/**
 *
 */
public class UrlMapping {

	private static final long DEFAULT_TIMEOUT = 30 * 24 * 60 * 60L;

	private Long id;

	private String shortUrl;

	private String longUrl;

	private Date createdTime;

	private Date expireTime;

	public UrlMapping(String shortUrl, String longUrl, Integer timeout) {
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.createdTime = new Date();
		expireTime = new Date(this.createdTime.getTime() + (timeout != null ? timeout * 60 : DEFAULT_TIMEOUT));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
