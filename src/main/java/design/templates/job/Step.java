package design.templates.job;

import design.templates.job.impl.ExecutionContext;

public interface Step<I, O> {
	void execute(ExecutionContext executionContext);
}
