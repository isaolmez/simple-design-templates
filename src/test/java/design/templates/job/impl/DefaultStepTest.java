package design.templates.job.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import design.templates.job.Processor;
import design.templates.job.Reader;
import design.templates.job.Step;
import design.templates.job.Writer;
import design.templates.job.builder.StepBuilder;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultStepTest {
	@Mock
	private Reader<Object> reader;

	@Mock
	private Writer<Object> writer;

	@Mock
	private Processor<Object, Object> processor;

	@Test
	public void shouldRunWriterAndOthers(){
		ExecutionContext context = new ExecutionContext();
		Step<Object, Object> step = StepBuilder.<Object, Object>newInstance()
				.reader(reader)
				.writer(writer)
				.processor(processor)
				.build();
		
		step.execute(context);
		
		verify(reader, times(1)).read(eq(context));
		verify(writer, times(1)).write(any(), eq(context));
		verify(processor, times(1)).process(any(), eq(context));
	}
}
