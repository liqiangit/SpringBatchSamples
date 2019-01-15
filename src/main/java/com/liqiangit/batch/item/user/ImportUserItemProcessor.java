package com.liqiangit.batch.item.user;

import java.io.File;

import org.springframework.batch.item.ItemProcessor;

public class ImportUserItemProcessor implements ItemProcessor<File,String>{

	@Override
	public String process(File item) throws Exception {
//		System.out.println(item.getName());
//		return item;
		return item.getName();
//		return "item";
	}

}
