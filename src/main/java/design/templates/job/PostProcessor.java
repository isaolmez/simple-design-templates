package design.templates.job;

import design.templates.job.impl.ExecutionContext;

public interface PostProcessor {
	void postProcess(ExecutionContext executionContext);
}
