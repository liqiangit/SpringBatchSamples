package org.springframework.batch.sample;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 参考multilineOrderJob.xml
 * 
 * @author YCKJ0224
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/simple-job-launcher-context.xml", "/jobs/cloudwalkImportUserJob.xml",
		"/job-runner-context.xml" })
public class CloudwalkImportUserTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	@Resource(name="jobLauncher")
	JobLauncher jobLauncher;
	@Resource(name="cloudwalkImportUserJob")
	Job job;
	static final String JOB_NAME = "jobName";

	@Test
	public void testJobLaunch() throws Exception {
		Map<String, Object> jobDataMap=new HashMap<String, Object>();
		jobDataMap.put("pathname", "E:\\images\\2018\\01\\imgauto2");
		JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
//		jobLauncherTestUtils.launchJob(jobParameters);
		jobLauncher.run(job, jobParameters);
	}

	private static JobParameters getJobParametersFromJobMap(Map<String, Object> jobDataMap) {

		JobParametersBuilder builder = new JobParametersBuilder();

		for (Entry<String, Object> entry : jobDataMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String && !key.equals(JOB_NAME)) {
				builder.addString(key, (String) value);
			} else if (value instanceof Float || value instanceof Double) {
				builder.addDouble(key, ((Number) value).doubleValue());
			} else if (value instanceof Integer || value instanceof Long) {
				builder.addLong(key, ((Number) value).longValue());
			} else if (value instanceof Date) {
				builder.addDate(key, (Date) value);
			} else {
				System.out.println("JobDataMap contains values which are not job parameters (ignoring).");
			}
		}

		return builder.toJobParameters();
	}
}
