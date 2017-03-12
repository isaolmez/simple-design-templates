package design.templates.job.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.job.PreProcessor;
import design.templates.job.impl.ExecutionContext;

public class SamplePreProcessor implements PreProcessor{
	private static final Logger LOG = LoggerFactory.getLogger(SamplePreProcessor.class);
	
	@Override
	public void preProcess(ExecutionContext executionContext) {
		boolean healthCheckEnabled = executionContext.getBoolean("healthCheckEnabled", false);
		if (healthCheckEnabled) {
			LOG.info("Performing health check");
			// Perform health check
		}
	}
}
