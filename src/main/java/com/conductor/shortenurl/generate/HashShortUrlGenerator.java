package com.conductor.shortenurl.generate;

import com.conductor.shortenurl.util.HashUtil;

public class HashShortUrlGenerator implements ShortUrlGenerator {
	@Override
	public String getType() {
		return GeneratorType.Hash.getName();
	}

	@Override
	public String generate(String longUrl) {
		return HashUtil.base62MurmurHash(longUrl);
	}
}
