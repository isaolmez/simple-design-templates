package design.templates.cache.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.cache.Cache;
import design.templates.cache.builder.CacheBuilders;

public class CacheClient {
	private static final Logger LOG = LoggerFactory.getLogger(CacheClient.class);
	public static void main(String[] args) {
		Cache<String, String> cache = CacheBuilders.<String, String>newInMemoryCacheBuilder()
				.expiresAfterRead(10000)
				.expiresAfterWrite(1000)
				.build();
		cache.put("test", "value");
		LOG.info(cache.get("test"));
	}
}
