package com.rkb.transform;

import java.util.Map;

public class Key2value {
	static String CALLBACKS ="callbacks";
	public static String key2value(String key,String value,Map<String, String> map,Boolean x) {
		System.out.println("key = "+key);
		String s = map.get(key);
		System.out.println("s ="+s);
		String answer = "";
		//直接填
		if (key.equals(CALLBACKS)&&x){
			return "";
		}
		else if (s.equals("")) {
			answer = s + value;
			return answer;
		}
		//布尔或数值
		if (s.equals(key+"=")) {
			if (value.equals("false")) {
				answer = s + "False";
			}else if (value.equals("true")) {
				answer = s + "True";
			}
			else {
				answer = s + value;
			}
			return answer;
		}
		//字符串
		if (s.charAt(0) == '@'){
			answer = s.substring(1)+"["+value+"]";
		}
		if (s.charAt(0) == '.') {
			if (value.equals("None")||key.equals("kernel_regularizer")) {
				answer = s.substring(1) + value;
			}else {
				answer = s.substring(1) + "'" +value + "'";
			}
		}
		
		
		
		return answer;
	}
	

}
