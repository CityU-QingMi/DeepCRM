	private Map<String, String> parseBody(String body) {
		String[] lines = body.split("\\n");
		Map<String, String> result = new LinkedHashMap<>(lines.length);
		for (String line : lines) {
			int idx = line.indexOf('=');
			String key = line.substring(0, idx);
			String value = line.substring(idx + 1);
			result.put(key, value);
		}
		return result;
	}
