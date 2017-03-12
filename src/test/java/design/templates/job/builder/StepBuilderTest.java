package design.templates.job.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import design.templates.job.Processor;
import design.templates.job.Reader;
import design.templates.job.Writer;

@RunWith(MockitoJUnitRunner.class)
public class StepBuilderTest {
	private StepBuilder<Object, Object> stepBuilder = StepBuilder.newInstance();
	
	@Mock
	private Reader<Object> reader;
	
	@Mock
	private Writer<Object> writer;
	
	@Mock
	private  Processor<Object, Object> processor;
	
	@Test
	public void shouldBuild(){
		stepBuilder.reader(reader).processor(processor).writer(writer).build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldNotBuildWithoutReader(){
		stepBuilder.processor(processor).writer(writer).build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldNotBuildWithoutProcessor(){
		stepBuilder.reader(reader).writer(writer).build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldNotBuildWithoutWriter(){
		stepBuilder.reader(reader).processor(processor).build();
	}
}
