	@SuppressWarnings("")
	public static <T> Matcher<T> exceptionCause(final Matcher<T> matcher) {
		return (Matcher<T>) new BaseMatcher<Object>() {
			@Override
			public boolean matches(Object item) {
				Throwable cause = null;
				if (item != null && item instanceof Throwable) {
					cause = ((Throwable)item).getCause();
				}
				return matcher.matches(cause);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("exception cause ").appendDescriptionOf(matcher);
			}
		};
	}
