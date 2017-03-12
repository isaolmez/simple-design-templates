package design.templates.job.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.job.PostProcessor;
import design.templates.job.impl.ExecutionContext;

public class SamplePostProcessor implements PostProcessor {
	private static final Logger LOG = LoggerFactory.getLogger(SamplePostProcessor.class);
	
	@Override
	public void postProcess(ExecutionContext executionContext) {
		boolean sendEmailEnabled = executionContext.getBoolean("sendEmailEnabled", false);
		if (sendEmailEnabled) {
			LOG.info("Sending email");
			// Send email
		}
	}

}
