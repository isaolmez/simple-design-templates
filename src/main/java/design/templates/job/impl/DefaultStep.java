package design.templates.job.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.job.PostProcessor;
import design.templates.job.PreProcessor;
import design.templates.job.Processor;
import design.templates.job.Reader;
import design.templates.job.Step;
import design.templates.job.Writer;

public class DefaultStep<I, O> implements Step<I, O> {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultStep.class);

	private final Reader<I> reader;
	private final Writer<O> writer;
	private final Processor<I, O> processor;
	private final List<PreProcessor> preProcessors;
	private final List<PostProcessor> postProcessors;

	public DefaultStep(Reader<I> reader, Writer<O> writer, Processor<I, O> processor) {
		this.reader = reader;
		this.writer = writer;
		this.processor = processor;
		this.preProcessors = Collections.unmodifiableList(new ArrayList<>());
		this.postProcessors = Collections.unmodifiableList(new ArrayList<>());
	}

	public DefaultStep(Reader<I> reader, Writer<O> writer, Processor<I, O> processor, List<PreProcessor> preProcessors,
			List<PostProcessor> postProcessors) {
		this.reader = reader;
		this.writer = writer;
		this.processor = processor;
		this.preProcessors = Collections.unmodifiableList(new ArrayList<>(preProcessors));
		this.postProcessors = Collections.unmodifiableList(new ArrayList<>(postProcessors));
	}

	@Override
	public void execute(ExecutionContext context) {
		LOG.info("Step started");
		preProcess(context);

		I item = reader.read(context);
		O output = processor.process(item, context);
		writer.write(output, context);

		postProcess(context);
		LOG.info("Step finished");
	}

	private void preProcess(ExecutionContext context) {
		this.preProcessors.stream().forEach(preProcessor -> preProcessor.preProcess(context));
	}

	private void postProcess(ExecutionContext context) {
		this.postProcessors.stream().forEach(postProcessor -> postProcessor.postProcess(context));
	}

}
