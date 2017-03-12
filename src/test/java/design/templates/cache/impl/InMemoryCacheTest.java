package design.templates.cache.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import design.templates.cache.Cache;
import design.templates.cache.LoadRule;
import design.templates.cache.builder.CacheBuilders;

public class InMemoryCacheTest {
	private Cache<String, String> cache;

	@Before
	public void setUp() {
		cache = CacheBuilders.<String, String> newInMemoryCacheBuilder().expiresAfterWrite(1000).expiresAfterRead(10)
				.loadRule(new LoadRule<String, String>() {
					@Override
					public String load(String key) {
						return "999";
					}
				}).build();
	}

	@Test
	public void shouldPut() {
		cache.put("a", "1");
	}
	
	@Test
	public void shouldGet() {
		cache.put("a", "1");
		
		String actual = cache.get("a");

		assertEquals("1", actual);
	}
	
	@Test
	public void shouldExpireAfterWrite() {
		final long sleepTime = 1001;
		cache.put("a", "1");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			fail();
		}
		
		String actual = cache.get("a");
		
		assertEquals("999", actual);
	}
	
	@Test
	public void shouldNotExpireAfterWrite() {
		final long sleepTime = 1;
		cache.put("a", "1");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			fail();
		}
		
		String actual = cache.get("a");
		
		assertEquals("1", actual);
	}
	
	@Test
	public void shouldExpireAfterRead() {
		final long sleepTime = 11;
		cache.put("a", "1");
		cache.get("a");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			fail();
		}
		
		String actual = cache.get("a");
		
		assertEquals("1", actual);
	}
	
	@Test
	public void shouldNotExpireAfterRead() {
		final long sleepTime = 1;
		cache.put("a", "1");
		cache.get("a");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			fail();
		}
		
		String actual = cache.get("a");
		
		assertEquals("1", actual);
	}
}
