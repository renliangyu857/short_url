package com.conductor.shortenurl.entity;

/*
 * @author: enping.jep
 * @create: 2023-04-18 3:01 PM
 */
public class Response {

  //返回码
  private Integer code;

  //返回信息
  private String msg;

  //业务数据
  private Object data;


  public static Response successs(String msg, Object data) {
    return new Response(200, msg, data);
  }

  public static Response create(Integer code, String msg) {
    return new Response(code, msg);
  }

  /**
   * 构造方法
   */
  public Response(Integer code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public Response(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }


  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
