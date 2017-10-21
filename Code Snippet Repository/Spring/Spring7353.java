	private List<String> getMatchingHeaderNames(String pattern, @Nullable Map<String, Object> headers) {
		List<String> matchingHeaderNames = new ArrayList<>();
		if (headers != null) {
			for (String key : headers.keySet()) {
				if (PatternMatchUtils.simpleMatch(pattern, key)) {
					matchingHeaderNames.add(key);
				}
			}
		}
		return matchingHeaderNames;
	}
