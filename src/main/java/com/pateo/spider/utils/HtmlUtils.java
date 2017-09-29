package com.pateo.spider.utils;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class HtmlUtils {
	
	/**
	 * 获取指定标签的内容
	 * @param rootNode
	 * @param xpath
	 * @return
	 */
	public static String getText(TagNode rootNode,String xpath){
		String result = null;
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath(xpath);
			if(evaluateXPath.length>0){
				TagNode tagNode = (TagNode)evaluateXPath[0];
				result = tagNode.getText().toString();
			}
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取指定标签指定属性的值
	 * @param rootNode
	 * @param xpath
	 * @param attr
	 * @return
	 */
	public static String getAttributeByName(TagNode rootNode,String xpath,String attr){
		String result = null;
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath(xpath);
			if(evaluateXPath.length>0){
				TagNode tagNode = (TagNode)evaluateXPath[0];
				result = tagNode.getAttributeByName(attr);
			}
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	

}
