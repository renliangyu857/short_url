package com.conductor.shortenurl.util;

/**
 * @author renliangyu857
 */
public class ShardingUtil {

    public static final int TBL_CNT = 4; //每个库四张表

    public static final int DB_CNT = 5;//5个库

    public static final int SUM_SLOT = DB_CNT * TBL_CNT;

}
