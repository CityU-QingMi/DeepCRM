	@SuppressWarnings({"", ""})
	private void merge(Map<String, Object> output, Map<String, Object> map) {
		map.forEach((key, value) -> {
			Object existing = output.get(key);
			if (value instanceof Map && existing instanceof Map) {
				// Inner cast required by Eclipse IDE.
				Map<String, Object> result = new LinkedHashMap<>((Map<String, Object>) existing);
				merge(result, (Map) value);
				output.put(key, result);
			}
			else {
				output.put(key, value);
			}
		});
	}
