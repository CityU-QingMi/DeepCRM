	private <K, V> String style(Map<K, V> value) {
		StringBuilder result = new StringBuilder(value.size() * 8 + 16);
		result.append(MAP + "[");
		for (Iterator<Map.Entry<K, V>> it = value.entrySet().iterator(); it.hasNext();) {
			Map.Entry<K, V> entry = it.next();
			result.append(style(entry));
			if (it.hasNext()) {
				result.append(',').append(' ');
			}
		}
		if (value.isEmpty()) {
			result.append(EMPTY);
		}
		result.append("]");
		return result.toString();
	}
