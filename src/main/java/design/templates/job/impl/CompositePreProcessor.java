package design.templates.job.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import design.templates.job.PreProcessor;

public class CompositePreProcessor implements PreProcessor {

	private final List<PreProcessor> preProcessors;

	private CompositePreProcessor(List<PreProcessor> preProcessors) {
		this.preProcessors = Collections.unmodifiableList(new ArrayList<>(preProcessors));
	}

	public static CompositePreProcessor get(List<PreProcessor> preProcessors) {
		List<PreProcessor> internalList = preProcessors;
		if(internalList == null){
			internalList = new ArrayList<>();
		}
		
		return new CompositePreProcessor(internalList);
	}

	@Override
	public void preProcess(ExecutionContext executionContext) {
		preProcessors.stream().forEachOrdered(preProcessor -> preProcessor.preProcess(executionContext));
	}

}
