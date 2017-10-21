	private static Set<TestGroup> parseGroups(String originalValue, String value) throws IllegalArgumentException {
		Set<TestGroup> groups = new HashSet<>();
		for (String group : value.split(",")) {
			try {
				groups.add(valueOf(group.trim().toUpperCase()));
			}
			catch (IllegalArgumentException ex) {
				throw new IllegalArgumentException(format(
						"Unable to find test group '%s' when parsing testGroups value: '%s'. " +
						"Available groups include: [%s]", group.trim(), originalValue,
						StringUtils.arrayToCommaDelimitedString(TestGroup.values())));
			}
		}
		return groups;
	}
