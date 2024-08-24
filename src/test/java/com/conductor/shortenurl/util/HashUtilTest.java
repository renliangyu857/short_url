package com.conductor.shortenurl.util;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author renliangyu857
 */
class HashUtilTest {

	private static final String url =
		"https://dpubstatic.udache.com/static/dpubimg/d4c0e518e95afb4e669affa6eb15d1d6/index.html";

	@Test
	public void murmurHashToBase62() {
		long hashCode = HashUtil.murmurHashStirng(url);
		assertTrue("4KpMst".equals(HashUtil.convertDecToBase62(hashCode)));
		long value = 100L << 26;
		System.out.println(value);
	}

	@Test
	public void generateUrl() {
		Faker faker = new Faker();
		System.out.println(faker.internet().url() + "/" + faker.number().randomNumber());
	}

	@Test
	public void testSubStr() {
		String str = "ds45";
		System.out.println(str.substring(2, str.length()));
	}
}