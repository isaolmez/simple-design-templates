package design.templates.job.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.job.Writer;
import design.templates.job.impl.ExecutionContext;

public class SampleWriter implements Writer<String> {
	private static final Logger LOG = LoggerFactory.getLogger(SampleWriter.class);

	@Override
	public void write(String record, ExecutionContext context) {
		LOG.info("Outputting record: {}", record);
	}
}
