package com.conductor.shortenurl.generate;

public interface ShortUrlGenerator {
	String getType();
	String generate(String longUrl);
}
