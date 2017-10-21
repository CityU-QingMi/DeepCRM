	@Nullable
	private static Collection<String> appendSlashes(@Nullable Collection<String> prefixes) {
		if (CollectionUtils.isEmpty(prefixes)) {
			return prefixes;
		}
		Collection<String> result = new ArrayList<>(prefixes.size());
		for (String prefix : prefixes) {
			if (!prefix.endsWith("/")) {
				prefix = prefix + "/";
			}
			result.add(prefix);
		}
		return result;
	}
