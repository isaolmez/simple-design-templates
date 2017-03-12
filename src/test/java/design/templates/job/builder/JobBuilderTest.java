package design.templates.job.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import design.templates.job.Step;

@RunWith(MockitoJUnitRunner.class)
public class JobBuilderTest {

	private JobBuilder jobBuilder = JobBuilder.newInstance();

	@Mock
	private Step<?, ?> step;

	@Test
	public void shouldBuild() {
		jobBuilder.next(step).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotBuild() {
		jobBuilder.build();
	}
}
