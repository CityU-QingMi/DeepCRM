	private Map<String, Class<?>> toClassMap(Map<String, ?> map) throws ClassNotFoundException {
		Map<String, Class<?>> result = new LinkedHashMap<>(map.size());
		for (Map.Entry<String, ?> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			Class<?> type;
			if (value instanceof Class) {
				type = (Class<?>) value;
			}
			else if (value instanceof String) {
				String className = (String) value;
				type = ClassUtils.forName(className, this.beanClassLoader);
			}
			else {
				throw new IllegalArgumentException("Unknown value [" + value + "] - expected String or Class");
			}
			result.put(key, type);
		}
		return result;
	}
