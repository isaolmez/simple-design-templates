package design.templates.job.client;

import java.util.Date;
import java.util.HashMap;

import com.google.common.collect.Lists;

import design.templates.job.Job;
import design.templates.job.Step;
import design.templates.job.builder.JobBuilder;
import design.templates.job.builder.StepBuilder;
import design.templates.job.impl.ExecutionContext;

public class JobClient {
	public static void main(String[] args) {
		Step<Date, String> step = StepBuilder.<Date, String>newInstance()
				.preProcessors(Lists.newArrayList(new SamplePreProcessor()))
				.reader(new SampleReader())
				.processor(new SampleProcessor())
				.writer(new SampleWriter())
				.postProcessors(Lists.newArrayList(new SamplePostProcessor()))
				.build();
		
		Job job = JobBuilder.newInstance().next(step).build();
		
		ExecutionContext context = new ExecutionContext();
		context.put("batchEnabled", true);
		context.put("batchSize", 5);
		context.put("healthCheckEnabled", true);
		context.put("externalUrls", Lists.newArrayList("http://www.google.com"));
		context.put("sendEmailEnabled", true);
		context.put("emailProperties", new HashMap<>());
		
		job.execute(context);
	}
}
