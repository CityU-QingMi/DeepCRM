	public static Set<TestGroup> parse(String value) throws IllegalArgumentException {
		if (!StringUtils.hasText(value)) {
			return Collections.emptySet();
		}
		String originalValue = value;
		value = value.trim();
		if ("ALL".equalsIgnoreCase(value)) {
			return EnumSet.allOf(TestGroup.class);
		}
		if (value.toUpperCase().startsWith("ALL-")) {
			Set<TestGroup> groups = EnumSet.allOf(TestGroup.class);
			groups.removeAll(parseGroups(originalValue, value.substring(4)));
			return groups;
		}
		return parseGroups(originalValue, value);
	}
