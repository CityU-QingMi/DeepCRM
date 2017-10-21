	private String convertToRegEx(String pattern) {
		pattern = Matcher.quoteReplacement(pattern);

		// Match "." against "." and "$" since users may declare a "." instead of a
		// "$" as the separator between classes and nested classes.
		pattern = pattern.replace(".", "[.$]");

		// Convert our "*" wildcard into a proper RegEx pattern.
		pattern = pattern.replace("*", ".+");

		return pattern;
	}
