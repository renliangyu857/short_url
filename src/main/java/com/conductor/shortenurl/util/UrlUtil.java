package com.conductor.shortenurl.util;

import java.util.regex.Pattern;

/**
 * @author renliangyu857
 */
public class UrlUtil {

  private static final Pattern URL_REG = Pattern.compile(
      "^(((ht|f)tps?):\\/\\/)?[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$");

  //验证字符串是否是一个url地址
  public static boolean checkURL(String url) {
    return URL_REG.matcher(url).matches();
  }
}