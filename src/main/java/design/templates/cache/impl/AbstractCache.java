package design.templates.cache.impl;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import design.templates.cache.Cache;

public abstract class AbstractCache<K, V> implements Cache<K, V> {

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		map.forEach(this::put);
	}

	@Override
	public Map<K, V> get(List<? extends K> keys) {
		Map<K, V> result = Maps.newLinkedHashMap();
		for (K key : keys) {
			if (!result.containsKey(key)) {
				V value = get(key);
				if (value != null) {
					result.put(key, value);
				}
			}
		}

		return ImmutableMap.copyOf(result);
	}
}
