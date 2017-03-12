package design.templates.job.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import design.templates.job.Job;
import design.templates.job.Step;
import design.templates.job.builder.JobBuilder;

@RunWith(MockitoJUnitRunner.class)
public class DefaultJobTest {
	private Job job;
	
	private ExecutionContext context = new ExecutionContext();
	
	@Test
	public void shouldRunAllSteps(){
		Step<Object, Object> step1 = mock(Step.class);
		Step<Object, Object> step2 = mock(Step.class);
		job = JobBuilder.newInstance().next(step1).next(step2).build();
		context.put("batchEnabled", true);
		context.put("batchSize", 5);
		
		job.execute(context);
		
		verify(step1, times(5)).execute(context);
		verify(step2, times(5)).execute(context);
	}
}
