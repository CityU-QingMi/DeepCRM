	private static Set<String> prependLeadingSlash(Collection<String> patterns, PathMatcher pathMatcher) {
		boolean slashSeparator = pathMatcher.combine("a", "a").equals("a/a");
		Set<String> result = new LinkedHashSet<>(patterns.size());
		for (String pattern : patterns) {
			if (slashSeparator) {
				if (StringUtils.hasLength(pattern) && !pattern.startsWith("/")) {
					pattern = "/" + pattern;
				}
			}
			result.add(pattern);
		}
		return result;
	}
