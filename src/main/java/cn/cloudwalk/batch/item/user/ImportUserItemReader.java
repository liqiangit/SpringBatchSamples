package cn.cloudwalk.batch.item.user;

import java.io.File;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

public class ImportUserItemReader extends AbstractItemStreamItemReader<File> {
	private File folder;
	private File[] files;
	private int i = 0;
	private String pathname;
	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}

	@Override
	public void open(ExecutionContext executionContext) {
//		String pathname="E:\\images\\2018\\01\\imgauto2";
		System.out.println("ImportUserItemReader.open");
		super.open(executionContext);
		folder = new File(pathname);
		files = folder.listFiles();
	}

	@Override
	public void update(ExecutionContext executionContext) {
		System.out.println("ImportUserItemReader.update");
		super.update(executionContext);
	}

	@Override
	public void close() {
		System.out.println("ImportUserItemReader.close");
		super.close();
	}

	@Override
	public File read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (i < files.length) {
			File file = files[i];
			i++;
			return file;
		}
		return null;
	}

}
