package design.templates.cache.builder;

import design.templates.cache.Cache;
import design.templates.cache.LoadRule;

public abstract class CacheBuilder<K, V> {
	private LoadRule<K, V> loadRule;
	
	private long expiresAfterWrite;
	
	private long expiresAfterRead;

	public CacheBuilder<K, V> expiresAfterWrite(long expiresAfterWrite) {
		this.expiresAfterWrite = expiresAfterWrite;
		return this;
	}
	
	public CacheBuilder<K, V> expiresAfterRead(long expiresAfterRead) {
		this.expiresAfterRead = expiresAfterRead;
		return this;
	}

	public CacheBuilder<K, V> loadRule(LoadRule<K, V> loadRule) {
		this.loadRule = loadRule;
		return this;
	}

	public abstract Cache<K, V> build();
	
	protected LoadRule<K, V> getLoadRule(){
		return loadRule;
	}

	protected long getExpiresAfterWrite() {
		return expiresAfterWrite;
	}

	protected long getExpiresAfterRead() {
		return expiresAfterRead;
	}
}
