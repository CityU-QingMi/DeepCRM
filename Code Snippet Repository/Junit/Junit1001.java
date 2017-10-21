	@Test
	void excludeClassNamePatternsWithSinglePattern() {
		String regex = "^java\\.lang\\..*";

		ClassNameFilter filter = ClassNameFilter.excludeClassNamePatterns(regex);

		assertThat(filter).hasToString("Excludes class names that match regular expression '" + regex + "'");

		assertTrue(filter.apply("java.lang.String").excluded());
		assertFalse(filter.toPredicate().test("java.lang.String"));

		assertThat(filter.apply("java.lang.String").getReason()).contains(
			"Class name [java.lang.String] matches excluded pattern: '" + regex + "'");

		assertTrue(filter.apply("java.time.Instant").included());
		assertTrue(filter.toPredicate().test("java.time.Instant"));
		assertThat(filter.apply("java.time.Instant").getReason()).contains(
			"Class name [java.time.Instant] does not match any excluded pattern: '" + regex + "'");
	}
