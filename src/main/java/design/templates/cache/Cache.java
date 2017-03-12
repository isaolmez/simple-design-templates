package design.templates.cache;

import java.util.List;
import java.util.Map;

public interface Cache<K, V> {
	void put(K key, V value);
	
	void putAll(Map<? extends K, ? extends V> map);
	
	V get(K key);
	
	Map<K, V> get(List<? extends K> keys);
	
	int size();
	
	void invalidateAll();
}
