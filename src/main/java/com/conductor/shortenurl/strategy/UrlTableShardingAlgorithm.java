package com.conductor.shortenurl.strategy;

import com.conductor.shortenurl.util.ShardingUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author renliangyu857
 */
public class UrlTableShardingAlgorithm extends CustomShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<String> preciseShardingValue) {
        for (String tableName : tableNames) {//url_mapping_0 ... url_mapping_3
            if (tableName.endsWith(String.valueOf(calSlot(preciseShardingValue) % ShardingUtil.TBL_CNT))) {
                return tableName;
            }
        }
        throw new IllegalArgumentException("分表计算时异常" + preciseShardingValue.toString());
    }
}
