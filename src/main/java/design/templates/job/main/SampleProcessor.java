package design.templates.job.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.job.Processor;
import design.templates.job.impl.ExecutionContext;

public class SampleProcessor implements Processor<Date, String>{
	private static final Logger LOG = LoggerFactory.getLogger(SampleProcessor.class);
	
	@Override
	public String process(Date input, ExecutionContext context) {
		LOG.info("Processing record");
		return new SimpleDateFormat("yyyyMMdd").format(input);
	}

}
