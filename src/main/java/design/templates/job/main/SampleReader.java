package design.templates.job.main;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.job.Reader;
import design.templates.job.impl.ExecutionContext;

public class SampleReader implements Reader<Date>{
	private static final Logger LOG = LoggerFactory.getLogger(SampleReader.class);
	
	@Override
	public Date read(ExecutionContext context) {
		LOG.info("Reading record");
		return new Date();
	}

}
