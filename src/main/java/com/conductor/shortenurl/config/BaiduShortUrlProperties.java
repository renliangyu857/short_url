package com.conductor.shortenurl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "sensin.uid")
public class BaiduShortUrlProperties {

	// timeBits、workerBits、seqBits三个值相加为63，剩余一位为sign，固定1bit符号标识，使生成的UID为正数
	// 时间长度
	private Integer timeBits = 30;

	// 机器id
	private Integer workerBits = 18;

	// 每秒下的并发序列
	private Integer seqBits = 15;

	// 格式：yyyy-MM-dd，这个值会转换成毫秒的时间戳
	private String epochStr = "2019-07-10";

	// RingBuffer size扩容参数, 可提高UID生成的吞吐量
	private Integer boostPower = 3;

	// 指定何时向RingBuffer中填充UID, 取值为百分比(0, 100)
	private Integer paddingFactor = 65;

	// 另外一种RingBuffer填充时机, 在Schedule线程中, 周期性检查填充。一般不使用这个属性，除非使用ID的频次固定。
	private Integer scheduleInterval;

	public Integer getTimeBits() {
		return timeBits;
	}

	public void setTimeBits(Integer timeBits) {
		this.timeBits = timeBits;
	}

	public Integer getWorkerBits() {
		return workerBits;
	}

	public void setWorkerBits(Integer workerBits) {
		this.workerBits = workerBits;
	}

	public Integer getSeqBits() {
		return seqBits;
	}

	public void setSeqBits(Integer seqBits) {
		this.seqBits = seqBits;
	}

	public String getEpochStr() {
		return epochStr;
	}

	public void setEpochStr(String epochStr) {
		this.epochStr = epochStr;
	}

	public Integer getBoostPower() {
		return boostPower;
	}

	public void setBoostPower(Integer boostPower) {
		this.boostPower = boostPower;
	}

	public Integer getPaddingFactor() {
		return paddingFactor;
	}

	public void setPaddingFactor(Integer paddingFactor) {
		this.paddingFactor = paddingFactor;
	}

	public Integer getScheduleInterval() {
		return scheduleInterval;
	}

	public void setScheduleInterval(Integer scheduleInterval) {
		this.scheduleInterval = scheduleInterval;
	}
}
