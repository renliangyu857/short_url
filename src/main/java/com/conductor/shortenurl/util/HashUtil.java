package com.conductor.shortenurl.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * @author renliangyu857
 */
public class HashUtil {

    private static final char[] CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final int SIZE = CHARS.length;

    /**
     * 将数字转换成62进制
     */
    public static String convertDecToBase62(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int i = (int) (num % SIZE);
            sb.append(CHARS[i]);
            num /= SIZE;
        }
        return sb.reverse().toString();
    }

    /**
     * 62进制转化成10进制
     *
     * @param numStr 62进制
     * @return 10进制
     */
    public static long base62ToLong(String numStr) {
        //最后转换完成之后的十进制数字
        long num = 0;
        //字符串中的具体某一个字符
        int idx;

        for (int i = 0; i < numStr.length(); i++) {
            idx = numStr.charAt(numStr.length() - 1 - i);

            if (idx >= 'a') {
                //idx = 'a' + remainder - 36，于是可以推导出：remainder = idx + 36 - 'a'
                //num = remainder * 62^i
                num += (idx + 36 - 'a') * Math.pow(62, i);
            } else if (idx >= 'A') {
                //idx = 'A' + remainder - 10，于是可以推导出：remainder = idx + 10 - 'A'
                num += (idx + 10 - 'A') * Math.pow(62, i);
            } else {
                //idx = '0' + remainder，于是可以推导出：remainder = idx - '0'
                num += (idx - '0') * Math.pow(62, i);
            }
        }
        return num;
    }

    /**
     * 用MurmurHash算法计算字符串的hash值
     * @param str 字符串
     * @return hash值
     */
    public static long murmurHashStirng(String str) {
        return Hashing.murmur3_32_fixed().hashString(str, StandardCharsets.UTF_8).padToLong();
    }

    public static String base62MurmurHash(String str) {
        return convertDecToBase62(murmurHashStirng(str));
    }

}
