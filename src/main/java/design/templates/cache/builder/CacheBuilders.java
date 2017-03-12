package design.templates.cache.builder;

public class CacheBuilders {
	private CacheBuilders(){
	}
	
	public static <K,V> CacheBuilder<K, V> newInMemoryCacheBuilder(){
		return InMemoryCacheBuilder.newInstance();
	}
}
