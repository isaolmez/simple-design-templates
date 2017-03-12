package design.templates.job.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import design.templates.job.PostProcessor;

public class CompositePostProcessor implements PostProcessor {

	private final List<PostProcessor> postProcessors;

	private CompositePostProcessor(List<PostProcessor> postProcessors) {
		this.postProcessors = Collections.unmodifiableList(new ArrayList<>(postProcessors));
	}

	public static CompositePostProcessor get(List<PostProcessor> postProcessors){
		List<PostProcessor> internalList = postProcessors;
		if(internalList == null){
			internalList = new ArrayList<>();
		}
		
		return new CompositePostProcessor(internalList);
	}
	
	@Override
	public void postProcess(ExecutionContext executionContext) {
		postProcessors.stream().forEachOrdered(postProcessor -> postProcessor.postProcess(executionContext));
	}

}
