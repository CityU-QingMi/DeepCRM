	@Test
	void includeClassNamePatternsChecksPreconditions() {
		assertThatThrownBy(() -> ClassNameFilter.includeClassNamePatterns((String[]) null)) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("patterns must not be null or empty");
		assertThatThrownBy(() -> ClassNameFilter.includeClassNamePatterns(new String[0])) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("patterns must not be null or empty");
		assertThatThrownBy(() -> ClassNameFilter.includeClassNamePatterns(new String[] { null })) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("patterns must not contain null elements");
	}
