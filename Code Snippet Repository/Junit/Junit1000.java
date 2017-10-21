	@Test
	void excludeClassNamePatternsChecksPreconditions() {
		assertThatThrownBy(() -> ClassNameFilter.excludeClassNamePatterns((String[]) null)) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("patterns must not be null or empty");
		assertThatThrownBy(() -> ClassNameFilter.excludeClassNamePatterns(new String[0])) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("patterns must not be null or empty");
		assertThatThrownBy(() -> ClassNameFilter.excludeClassNamePatterns(new String[] { null })) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("patterns must not contain null elements");
	}
