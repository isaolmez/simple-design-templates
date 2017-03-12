package design.templates.cache;

@FunctionalInterface
public interface LoadRule<K, V> {
	V load(K key);
}
