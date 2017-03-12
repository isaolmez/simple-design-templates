package design.templates.job.impl;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ExecutionContext {
	private ConcurrentMap<String, Object> map;

	public ExecutionContext() {
		map = new ConcurrentHashMap<>();
	}

	public ExecutionContext(Map<String, Object> map) {
		this.map = new ConcurrentHashMap<>(map);
	}

	public ExecutionContext(ExecutionContext executionContext) {
		this();
		if (executionContext == null) {
			return;
		}
		for (Entry<String, Object> entry : executionContext.entrySet()) {
			this.map.put(entry.getKey(), entry.getValue());
		}
	}

	public void putString(String key, String value) {

		put(key, value);
	}

	public void putLong(String key, long value) {

		put(key, Long.valueOf(value));
	}

	public void putInt(String key, int value) {
		put(key, Integer.valueOf(value));
	}

	public void putDouble(String key, double value) {

		put(key, Double.valueOf(value));
	}

	public void put(String key, Object value) {
		if (value != null) {
			map.put(key, value);
		} else {
			map.remove(key);
		}
	}

	public String getString(String key) {
		return (String) readAndValidate(key, String.class);
	}

	public String getString(String key, String defaultString) {
		if (!map.containsKey(key)) {
			return defaultString;
		}

		return (String) readAndValidate(key, String.class);
	}

	public long getLong(String key) {

		return ((Long) readAndValidate(key, Long.class)).longValue();
	}

	public long getLong(String key, long defaultLong) {
		if (!map.containsKey(key)) {
			return defaultLong;
		}

		return ((Long) readAndValidate(key, Long.class)).longValue();
	}

	public int getInt(String key) {

		return ((Integer) readAndValidate(key, Integer.class)).intValue();
	}

	public int getInt(String key, int defaultInt) {
		if (!map.containsKey(key)) {
			return defaultInt;
		}

		return ((Integer) readAndValidate(key, Integer.class)).intValue();
	}

	public double getDouble(String key) {
		return ((Double) readAndValidate(key, Double.class)).doubleValue();
	}

	public double getDouble(String key, double defaultDouble) {
		if (!map.containsKey(key)) {
			return defaultDouble;
		}

		return ((Double) readAndValidate(key, Double.class)).doubleValue();
	}

	public boolean getBoolean(String key) {
		return ((Boolean) readAndValidate(key, Boolean.class)).booleanValue();
	}

	public boolean getBoolean(String key, boolean defaultBoolean) {
		if (!map.containsKey(key)) {
			return defaultBoolean;
		}

		return ((Boolean) readAndValidate(key, Boolean.class)).booleanValue();
	}

	public Object get(String key) {
		return map.get(key);
	}

	private Object readAndValidate(String key, Class<?> type) {

		Object value = map.get(key);

		if (!type.isInstance(value)) {
			throw new ClassCastException("Value for key=[" + key + "] is not of type: [" + type + "], it is ["
					+ (value == null ? null : "(" + value.getClass() + ")" + value) + "]");
		}

		return value;
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set<Entry<String, Object>> entrySet() {
		return map.entrySet();
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public Object remove(String key) {
		return map.remove(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ExecutionContext == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		ExecutionContext rhs = (ExecutionContext) obj;
		return this.entrySet().equals(rhs.entrySet());
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	@Override
	public String toString() {
		return map.toString();
	}

	public int size() {
		return map.size();
	}
}
