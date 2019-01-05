package cn.cloudwalk.batch.item.user;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	private List<File> list = new ArrayList<File>();
	private int currentItemCount = 0;

	private int maxItemCount = 0;

	private String pathname;

	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}

	@Override
	public void open(ExecutionContext executionContext) {
		super.open(executionContext);
		System.out.println("ImportUserItemReader.open");
		long start = System.currentTimeMillis();
//		String pathname="E:\\images\\2018\\01\\imgauto2";
		super.open(executionContext);
		File folder = new File(pathname);
		traverseFolder(list, folder, new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".jpg");
			}
		});
		maxItemCount = list.size();
		executionContext.putInt("currentItemCount", currentItemCount);
		executionContext.putInt("maxItemCount", maxItemCount);
		System.out.println(String.format("list.size()=%s", list.size()));
		System.out.println("耗时" + (System.currentTimeMillis() - start));
		System.out.println();
	}

	@Override
	public void update(ExecutionContext executionContext) {
		super.update(executionContext);
		System.out.println("ImportUserItemReader.update");
		executionContext.putInt("currentItemCount", currentItemCount);
	}
	@Override
	public void close() {
		super.close();
		System.out.println("ImportUserItemReader.close");
	}

	@Override
	public File read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (currentItemCount < list.size()) {
			File file = list.get(currentItemCount);
			currentItemCount++;
			return file;
		}
		return null;
	}

	/**
	 * 递归遍历
	 * 
	 * @param list
	 * @param folder
	 * @param fileFilter
	 */
	public void traverseFolder(List<File> list, File folder, FileFilter fileFilter) {
		if (folder.exists()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					traverseFolder(list, file, fileFilter);
				} else {
					if (fileFilter.accept(file)) {
						list.add(file);
					}
				}
			}
		}
	}
}
