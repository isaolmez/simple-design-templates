package design.templates.job;

import design.templates.job.impl.ExecutionContext;

public interface Reader<T> {
	T read(ExecutionContext context);
}
