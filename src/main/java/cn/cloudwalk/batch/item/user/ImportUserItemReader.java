package cn.cloudwalk.batch.item.user;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

public class ImportUserItemReader extends AbstractItemStreamItemReader<File> {
//	private File folder;
//	private File[] files;
	private List<File> list=new ArrayList<File>();
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
		long start=System.currentTimeMillis();
//		String pathname="E:\\images\\2018\\01\\imgauto2";
		System.out.println("ImportUserItemReader.open");
		super.open(executionContext);
		File folder = new File(pathname);
		traverseFolder(list,folder,new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".jpg");
			}
		});
		System.out.println(String.format("list.size()=%s", list.size()));
		System.out.println("耗时"+(System.currentTimeMillis()-start));
		System.out.println();
	}

	public void traverseFolder(List<File> list,File folder,FileFilter fileFilter) {
		if (folder.exists()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					traverseFolder(list,file,fileFilter);
				} else {
					if(fileFilter.accept(file)) {
						list.add(file);
					}
				}
			}
		}
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
		if (i < list.size()) {
			File file = list.get(i);
			i++;
			return file;
		}
		return null;
	}

}
