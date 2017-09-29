package com.pateo.spider.store;

import java.io.IOException;
import java.util.Map;

import com.pateo.spider.domain.Page;
import com.pateo.spider.utils.HbaseUtils;
/**
 * create 'spider','goodsinfo','spec'
 * 修改列簇的版本数量
 * alter 'spider' ,{NAME=>'goodsinfo',VERSIONS=>30}
 * @author Administrator
 *
 */
public class HbaseStore implements Storeable {

	HbaseUtils hbaseUtils = new HbaseUtils();
	@Override
	public void store(Page page) {
		String goodsId = page.getGoodsId();
		Map<String, String> values = page.getValues();
		try {
			hbaseUtils.put(HbaseUtils.TABLE_NAME, goodsId, HbaseUtils.COLUMNFAMILY_1, HbaseUtils.COLUMNFAMILY_1_DATA_URL, page.getUrl());
			hbaseUtils.put(HbaseUtils.TABLE_NAME, goodsId, HbaseUtils.COLUMNFAMILY_1, HbaseUtils.COLUMNFAMILY_1_PIC_URL, values.get("picurl"));
			hbaseUtils.put(HbaseUtils.TABLE_NAME, goodsId, HbaseUtils.COLUMNFAMILY_1, HbaseUtils.COLUMNFAMILY_1_PRICE, values.get("price"));
			hbaseUtils.put(HbaseUtils.TABLE_NAME, goodsId, HbaseUtils.COLUMNFAMILY_1, HbaseUtils.COLUMNFAMILY_1_TITLE, values.get("title"));
			hbaseUtils.put(HbaseUtils.TABLE_NAME, goodsId, HbaseUtils.COLUMNFAMILY_2, HbaseUtils.COLUMNFAMILY_2_PARAM, values.get("spec"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
