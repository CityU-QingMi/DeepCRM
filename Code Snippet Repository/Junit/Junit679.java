	private String encode(String s) {
		StringBuilder builder = new StringBuilder();
		for (char c : s.toCharArray()) {
			String value = encodedCharacterMap.get(c);
			if (value == null) {
				builder.append(c);
				continue;
			}
			builder.append(value);
		}
		return builder.toString();
	}
