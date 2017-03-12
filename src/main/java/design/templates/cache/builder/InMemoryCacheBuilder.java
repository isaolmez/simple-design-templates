package design.templates.cache.builder;

import design.templates.cache.Cache;
import design.templates.cache.impl.InMemoryCache;

public class InMemoryCacheBuilder<K, V> extends CacheBuilder<K, V> {

	private InMemoryCacheBuilder() {
	}

	public static <K, V> InMemoryCacheBuilder<K, V> newInstance() {
		return new InMemoryCacheBuilder<>();
	}

	public Cache<K, V> build() {
		return new InMemoryCache<>(getExpiresAfterWrite(), getExpiresAfterRead(), getLoadRule());
	}
}
