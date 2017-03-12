package design.templates.job;

import design.templates.job.impl.ExecutionContext;

public interface PreProcessor {
	void preProcess(ExecutionContext executionContext);
}
