package com.conductor.shortenurl.context;

/**
 * @author renliangyu857
 */
public class UserContextHolder {

  //存放hashcode的缓存
  public static ThreadLocal<Long> hashCodeHolder = new ThreadLocal<>();

  //存放slot的缓存
  public static ThreadLocal<Long> slotHolder = new ThreadLocal<>();


}
