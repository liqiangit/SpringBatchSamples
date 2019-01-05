package cn.cloudwalk.batch.item.user;

import java.io.File;

import org.springframework.batch.item.ItemProcessor;

public class ImportUserItemProcessor implements ItemProcessor<File,File>{

	@Override
	public File process(File item) throws Exception {
		System.out.println(item.getName());
		return item;
	}

}
