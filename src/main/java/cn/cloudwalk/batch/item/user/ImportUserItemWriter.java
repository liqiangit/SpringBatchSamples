package cn.cloudwalk.batch.item.user;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

public class ImportUserItemWriter extends AbstractItemStreamItemWriter<String> {
	@Override
	public void open(ExecutionContext executionContext) {
		super.open(executionContext);
		System.out.println("ImportUserItemWriter.open");
	}

	@Override
	public void update(ExecutionContext executionContext) {
		super.update(executionContext);
		System.out.println("ImportUserItemWriter.update");
		progress(executionContext);
	}

	private void progress(ExecutionContext executionContext) {
		//异常会导致job终止，并且不会输出日志，所以要在catch中输出日志
		try {
			int currentItemCount=executionContext.getInt("currentItemCount");
			int maxItemCount=executionContext.getInt("maxItemCount");
			if(maxItemCount>0) {
				BigDecimal progress =new BigDecimal(String.valueOf(currentItemCount)).divide(new BigDecimal(String.valueOf(maxItemCount)),2, RoundingMode.HALF_UP);//.setScale(2, RoundingMode.HALF_UP);
				System.err.println(progress);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		super.close();
		System.out.println("ImportUserItemWriter.close");
	}

	@Override
	public void write(List<? extends String> items) throws Exception {
//		for (File file : items) {
//			System.out.println(file.getAbsolutePath());
//		}
//		for (String string : items) {
//			System.out.println(string);
//		}
		System.out.println(items.size());
	}

}
