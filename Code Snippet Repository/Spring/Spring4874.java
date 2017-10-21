	public static String[] removeDuplicateStrings(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return array;
		}

		Set<String> set = new LinkedHashSet<>();
		for (String element : array) {
			set.add(element);
		}
		return toStringArray(set);
	}
