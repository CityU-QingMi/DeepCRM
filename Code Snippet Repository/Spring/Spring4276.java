	private int getDepth(Class<?> declaredException, Class<?> exceptionToMatch, int depth) {
		if (exceptionToMatch.equals(declaredException)) {
			// Found it!
			return depth;
		}
		// If we've gone as far as we can go and haven't found it...
		if (exceptionToMatch == Throwable.class) {
			return Integer.MAX_VALUE;
		}
		return getDepth(declaredException, exceptionToMatch.getSuperclass(), depth + 1);
	}
