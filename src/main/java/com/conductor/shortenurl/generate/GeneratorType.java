package com.conductor.shortenurl.generate;

/**
 * 生成类型
 */
public enum GeneratorType {
	/**
	 * Hash方式，与长链接有关联性，生成随机正态分布
	 */
	Hash("hash"),

	/**
	 * 全局唯一ID方式，效率高，但存在一定的安全风险
	 */
	AUTO_INCR("autoIncr");

	private String name;

	private GeneratorType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static GeneratorType fromName(String name) {
		for (GeneratorType generatorType : values()) {
			if (generatorType.getName().equalsIgnoreCase(name)) {
				return generatorType;
			}
		}
		return null;
	}
}
