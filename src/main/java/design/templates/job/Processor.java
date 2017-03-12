package design.templates.job;

import design.templates.job.impl.ExecutionContext;

public interface Processor<I, O> {
	O process(I record, ExecutionContext context);
}
