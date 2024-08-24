package com.conductor.shortenurl.strategy;

import com.conductor.shortenurl.util.ShardingUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author renliangyu857
 */
public class UrlDBShardingAlgorithm extends CustomShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    private static final String DS = "ds";

    /**
     * @param databaseNames        数据源集合 在分库时值为所有分片库的集合 databaseNames ds0 - ds4
     * @param preciseShardingValue 分片属性，包括 logicTableName 为逻辑表， columnName 分片健（字段）， value 为从 SQL
     *                             中解析出的分片健的值
     */
    // insert into url_mapping values('123abc',"www.baidu.com/xxxx.html")
    @Override
    public String doSharding(Collection<String> databaseNames, PreciseShardingValue<String> preciseShardingValue) {
        long slot = calSlot(preciseShardingValue) / ShardingUtil.TBL_CNT;
        String databaseName = DS + String.valueOf(slot);
        if (databaseNames.contains(databaseName)) {
            return databaseName;
        }
        throw new IllegalArgumentException("没有找到库" + preciseShardingValue.toString());
    }
}
