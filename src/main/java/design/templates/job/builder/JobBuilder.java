package design.templates.job.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.base.Preconditions;

import design.templates.job.Job;
import design.templates.job.Step;
import design.templates.job.impl.DefaultJob;

public class JobBuilder {
	private List<Step<?, ?>> steps = new ArrayList<>();

	private JobBuilder() {
	}

	public static JobBuilder newInstance() {
		return new JobBuilder();
	}

	public JobBuilder next(Step<?, ?> step) {
		this.steps.add(step);
		return this;
	}

	public Job build() {
		Preconditions.checkArgument(CollectionUtils.isNotEmpty(steps));
		return new DefaultJob(steps);
	}
}
