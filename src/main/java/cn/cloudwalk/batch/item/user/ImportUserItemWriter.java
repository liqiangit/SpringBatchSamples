package cn.cloudwalk.batch.item.user;

import java.io.File;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

public class ImportUserItemWriter extends AbstractItemStreamItemWriter<File> {
	@Override
	public void open(ExecutionContext executionContext) {
		System.out.println("ImportUserItemWriter.open");
		super.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) {
		System.out.println("ImportUserItemWriter.update");
		super.update(executionContext);
	}

	@Override
	public void close() {
		System.out.println("ImportUserItemWriter.close");
		super.close();
	}

	@Override
	public void write(List<? extends File> items) throws Exception {
//		for (File file : items) {
//			System.out.println(file.getAbsolutePath());
//		}
		System.out.println(items.size());
	}

}
