package design.templates.job.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.job.Job;
import design.templates.job.Step;

public class DefaultJob implements Job {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultJob.class);

	private List<Step<?, ?>> steps;

	public DefaultJob() {
		this.steps = Collections.unmodifiableList(new ArrayList<>());
	}

	public DefaultJob(List<Step<?, ?>> steps) {
		this.steps = Collections.unmodifiableList(new ArrayList<>(steps));
	}

	@Override
	public void execute(ExecutionContext executionContext) {
		LOG.info("Job started");
		int batchSize = -1;
		boolean batchEnabled = executionContext.getBoolean("batchEnabled", false);
		if (batchEnabled) {
			batchSize = executionContext.getInt("batchSize", -1);
		}
		
		while (batchSize-- != 0) {
			steps.stream().forEach(step -> step.execute(executionContext));
		}
		
		LOG.info("Job finished");
	}
}
