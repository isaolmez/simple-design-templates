package design.templates.job;

import design.templates.job.impl.ExecutionContext;

public interface Writer<T> {
	void write(T record, ExecutionContext context);
}
