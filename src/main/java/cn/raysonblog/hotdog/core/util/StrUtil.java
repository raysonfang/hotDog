package cn.raysonblog.hotdog.core.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author rayson
 *
 */
public class StrUtil {
	/**
	 * 判断空数组
	 * @param arr 数组
	 * @return
	 */
	public static boolean isEmptyArray(Object[] arr){
		boolean flag = true;
		if(arr != null && arr.length > 0){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 根据业务编码 获取外部订单号
	 * @param businessNum
	 * @return
	 */
	public static String getOutTradeNo(String businessNum){
	    return businessNum + System.currentTimeMillis() + (long) (Math.random() * 10000000L);
	}
	
	
	
	/** js 微信 JS-SDK token 字符串
	 * @param takeInfo token 字符串
	 * @param startStr 开始分割字符串
	 * @param endStr 结束分割字符串
	 * @return  解析过后的字符串
	 */
	public static String GetPartStr(String takeInfo,String startStr, String endStr){
		String str = "";
		int strpos1 = takeInfo.indexOf(startStr);
		int strpos2 = takeInfo.indexOf(endStr);
		if(strpos1 == -1 || strpos2 == -1){
			return str;
		}
		str = takeInfo.substring(strpos1+startStr.length(), strpos2);
		return str;
	}
	
	
	/** 将map转换成为字符串
	 * @param paraMap
	 * @param isURLEncode
	 * @return
	 */
	public static String formatQueryParaMap(Map<String, String> paraMap, boolean isURLEncode) {
		String returnValue = null;
		try {
			List<Entry<String, String>> infoIds = new ArrayList<Entry<String, String>>(paraMap.entrySet());
			Collections.sort(infoIds, new Comparator<Entry<String, String>>() {
				public int compare(Entry<String, String> o1, Entry<String, String> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < infoIds.size(); i++) {
				Entry<String, String> item = infoIds.get(i);
				if (null != item.getKey() && !"".equals(item.getKey())) {
					String key = item.getKey();
					String val = item.getValue();
					if (isURLEncode) {
						val = URLEncoder.encode(val, "UTF-8");
						/* �ո�ͨ��URLEncoderת����ǡ�+��,����Ҫ�滻�ɡ�%20�� */
						val = val.replace("+", "%20");
					}
					buff.append(key + "=" + val + "&");
				}
			}
			returnValue = buff.toString();
			if (null != returnValue && !"".equals(returnValue)) {
				returnValue = returnValue.substring(0, returnValue.length() - 1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return returnValue;
	}
	
	/**
	 * 驼峰命名转下划线命名
	 * @param para 驼峰命名的字符串
	 * @return 下划线命名的字符串
	 */
	public static String HumpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        for(int i=0;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toLowerCase(); 
	}
	
	/**
	 * 将string数组转化为int数组
	 * @param strings string数组
	 * @return int数组
	 */
	public static Integer[] arrString2Int(String[] strings) {
		Integer[] ints=new Integer[strings.length];
		for(int i=0;i<strings.length;i++) {
			ints[i]=Integer.parseInt(strings[i]);
		}
		return ints;
	}
	
	/**
	 * 校验字符串是否是有效的邮箱
	 * @param string
	 * @return
	 */
	public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }
}
