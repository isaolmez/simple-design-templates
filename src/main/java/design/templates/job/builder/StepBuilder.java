package design.templates.job.builder;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

import design.templates.job.PostProcessor;
import design.templates.job.PreProcessor;
import design.templates.job.Processor;
import design.templates.job.Reader;
import design.templates.job.Step;
import design.templates.job.Writer;
import design.templates.job.impl.DefaultStep;

public class StepBuilder<I, O> {
	private Reader<I> reader;
	private Writer<O> writer;
	private Processor<I, O> processor;
	private List<PreProcessor> preProcessors = new ArrayList<>();
	private List<PostProcessor> postProcessors = new ArrayList<>();
	
	private StepBuilder(){
	}
	
	public static <I,O> StepBuilder<I, O> newInstance(){
		return new StepBuilder<>();
	}
	
	public StepBuilder<I, O> reader(Reader<I> reader){
		this.reader = reader;
		return this;
	}
	
	public StepBuilder<I, O> writer(Writer<O> writer){
		this.writer = writer;
		return this;
	}
	
	public StepBuilder<I, O> processor(Processor<I,O> processor){
		this.processor = processor;
		return this;
	}
	
	public StepBuilder<I, O> preProcessors(List<PreProcessor> preProcessors){
		this.preProcessors = new ArrayList<>(preProcessors);
		return this;
	}
	
	public StepBuilder<I, O> postProcessors(List<PostProcessor> postProcessors){
		this.postProcessors = new ArrayList<>(postProcessors);
		return this;
	}
	
	public StepBuilder<I, O> addPreProcessor(PreProcessor preProcessor){
		this.preProcessors.add(preProcessor);
		return this;
	}
	
	public StepBuilder<I, O> addPostProcessor(PostProcessor postProcessor){
		this.postProcessors.add(postProcessor);
		return this;
	}
	
	public Step<I, O> build(){
		Preconditions.checkNotNull(reader);
		Preconditions.checkNotNull(processor);
		Preconditions.checkNotNull(writer);
		if(preProcessors == null){
			preProcessors = new ArrayList<>();
		}
		
		if(postProcessors == null){
			postProcessors = new ArrayList<>();
		}
		
		return new DefaultStep<>(reader, writer, processor, preProcessors, postProcessors);
	}
}
