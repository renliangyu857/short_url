package com.conductor.shortenurl.strategy;

import com.conductor.shortenurl.util.HashUtil;
import com.conductor.shortenurl.util.ShardingUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

/**
 * @author renliangyu857
 */
public abstract class CustomShardingAlgorithm {

    /**
     * 计算分片
     * @param preciseShardingValue
     * @return
     */
    public long calSlot(PreciseShardingValue<String> preciseShardingValue) {
        long hashCode = HashUtil.base62ToLong(preciseShardingValue.getValue());//十进制
        return Math.abs(hashCode % ShardingUtil.SUM_SLOT);//分片序号
    }

}
