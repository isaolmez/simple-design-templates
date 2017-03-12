package design.templates.cache.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.templates.cache.LoadRule;

public class InMemoryCache<K, V> extends AbstractCache<K, V> {
	private static final Logger LOG = LoggerFactory.getLogger(InMemoryCache.class);
	
	private final ConcurrentMap<K, CacheValue<V>> cache;

	private final long expiresAfterWrite;

	private final long expiresAfterRead;

	private final LoadRule<K, V> loadRule;
	
	private final Object lock = new Object();

	public InMemoryCache(long expiresAfterWrite, long expiresAfterRead, LoadRule<K, V> loadRule) {
		cache = new ConcurrentHashMap<>();
		this.expiresAfterRead = expiresAfterRead;
		this.expiresAfterWrite = expiresAfterWrite;
		this.loadRule = loadRule;
	}

	@Override
	public void put(K key, V value) {
		if(value == null){
			cache.remove(key);
		}else{
			cache.put(key, new CacheValue<>(value));
		}
		
	}

	@Override
	public V get(K key) {
		checkForExpiry(key);
		CacheValue<V> cacheValue = cache.get(key);
		return cacheValue == null ? null : cacheValue.get();
	}

	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public void invalidateAll() {
		cache.clear();
	}

	private static class CacheValue<V> {
		private final V value;

		private long lastReadTime;

		private long lastWriteTime;

		public CacheValue(V value) {
			this.value = value;
			this.lastWriteTime = System.currentTimeMillis();
		}

		public V get() {
			lastReadTime = System.currentTimeMillis();
			return value;
		}
	}

	private void checkForExpiry(K key) {
		CacheValue<V> cacheValue = cache.get(key);
		if (isExpired(cacheValue)) {
			synchronized (lock) {
				if(isExpired(cacheValue)){
					load(key);
				}
			}
		}
	}

	private boolean isExpired(CacheValue<V> cacheValue){
		return cacheValue != null && (isExpired(cacheValue.lastWriteTime, expiresAfterWrite, cacheValue.lastReadTime, expiresAfterRead));
	}
	
	private boolean isExpired(long lastWriteTime, long expiresAfterWrite, long lastReadTime, long expiresAfterRead) {
		long now = System.currentTimeMillis();
		final long effectiveLastReadTime = lastReadTime == 0 ? now : lastReadTime;
		LOG.info("{}-{}-{}-{}", now - lastWriteTime, expiresAfterWrite, now - effectiveLastReadTime, expiresAfterRead);
		return now - lastWriteTime >= expiresAfterWrite || now - effectiveLastReadTime >= expiresAfterRead;
	}

	private void load(K key) {
		V value = loadRule.load(key);
		put(key, value);
	}
}
