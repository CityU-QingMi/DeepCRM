	@Test
	void includeClassNamePatternsWithSinglePattern() {
		String regex = "^java\\.lang\\..*";

		ClassNameFilter filter = ClassNameFilter.includeClassNamePatterns(regex);

		assertThat(filter).hasToString("Includes class names that match regular expression '" + regex + "'");

		assertTrue(filter.apply("java.lang.String").included());
		assertTrue(filter.toPredicate().test("java.lang.String"));
		assertThat(filter.apply("java.lang.String").getReason()).contains(
			"Class name [java.lang.String] matches included pattern: '" + regex + "'");

		assertFalse(filter.apply("java.time.Instant").included());
		assertFalse(filter.toPredicate().test("java.time.Instant"));
		assertThat(filter.apply("java.time.Instant").getReason()).contains(
			"Class name [java.time.Instant] does not match any included pattern: '" + regex + "'");
	}
