package cn.raysonblog.hotdog.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具类
 * @author dell
 *
 */
public class RegexMatcher {
	
	/**
	 * 构造方法
	 * @param pattern
	 * @param input
	 */
	public RegexMatcher(String pattern, String input){
		setPattern(Pattern.compile(pattern));
		setInput(input);
	}
	
	/**
	 * pattern对象
	 */
	private Pattern pattern;
		
	public Pattern getPattren(){
		return this.pattern;
	}
		
	public void setPattern(Pattern pattern){
		this.pattern = pattern;
	}
		
	private String input;
		
	public String getInput(){
		return this.input;
	}
		
	public void setInput(String input){
		this.input = input;
	}
		
	public boolean isMatch(){
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}
