package com.conductor.shortenurl.generate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

public class ShortUrlGenerateFactory {
	public static ShortUrlGenerator getInstance(String type) {
		GeneratorType generatorType = GeneratorType.fromName(type);
		return ShortUrlGeneratorHolder.generators
			.get(generatorType == null ? GeneratorType.Hash.getName() : generatorType.getName());
	}

	private static Map<String, ShortUrlGenerator> buildGenerators() {
		Map<String, ShortUrlGenerator> result = new HashMap<>(2);
		// HASH -> HashShot
		// INCR -> AUtoIncr
		//SPI
		ServiceLoader<ShortUrlGenerator> generators = ServiceLoader.load(ShortUrlGenerator.class);
		//自定义类加载器，从指定的路径去加载类
		Iterator<ShortUrlGenerator> iterator = generators.iterator();
		while (iterator.hasNext()) {
			ShortUrlGenerator generator = iterator.next();
			result.put(generator.getType(), generator);
		}
		return result;
	}

	private static class ShortUrlGeneratorHolder {
		private static final Map<String, ShortUrlGenerator> generators = buildGenerators();
	}
}
