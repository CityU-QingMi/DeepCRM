	private static Pattern buildShallowIndexPattern(String pattern, boolean wordBoundary) {
		return Pattern.compile(
				"(" +
				( wordBoundary ? "\\b" : "" ) +
				pattern +
				( wordBoundary ? "\\b" : "" ) +
				")(?![^\\(|\\[]*(\\)|\\]))",
				Pattern.CASE_INSENSITIVE
		);
	}
