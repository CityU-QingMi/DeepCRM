	private String[] getIncludeClassNamePatterns(boolean isSuite) {
		String[] patterns = getValueFromAnnotation(IncludeClassNamePatterns.class, IncludeClassNamePatterns::value,
			new String[0]);
		if (patterns.length == 0 && isSuite) {
			return new String[] { STANDARD_INCLUDE_PATTERN };
		}
		Preconditions.containsNoNullElements(patterns, "IncludeClassNamePatterns must not contain null elements");
		trim(patterns);
		return patterns;
	}
