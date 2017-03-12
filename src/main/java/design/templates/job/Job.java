package design.templates.job;

import design.templates.job.impl.ExecutionContext;

public interface Job {
	void execute(ExecutionContext executionContext);
}
