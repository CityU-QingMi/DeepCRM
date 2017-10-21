	@Test
	void includeClassNamePatternsWithMultiplePatterns() {
		String firstRegex = "^java\\.lang\\..*";
		String secondRegex = "^java\\.util\\..*";

		ClassNameFilter filter = ClassNameFilter.includeClassNamePatterns(firstRegex, secondRegex);

		assertThat(filter).hasToString(
			"Includes class names that match regular expression '" + firstRegex + "' OR '" + secondRegex + "'");

		assertTrue(filter.apply("java.lang.String").included());
		assertTrue(filter.toPredicate().test("java.lang.String"));
		assertThat(filter.apply("java.lang.String").getReason()).contains(
			"Class name [java.lang.String] matches included pattern: '" + firstRegex + "'");

		assertTrue(filter.apply("java.util.Collection").included());
		assertTrue(filter.toPredicate().test("java.util.Collection"));
		assertThat(filter.apply("java.util.Collection").getReason()).contains(
			"Class name [java.util.Collection] matches included pattern: '" + secondRegex + "'");

		assertFalse(filter.apply("java.time.Instant").included());
		assertFalse(filter.toPredicate().test("java.time.Instant"));
		assertThat(filter.apply("java.time.Instant").getReason()).contains(
			"Class name [java.time.Instant] does not match any included pattern: '" + firstRegex + "' OR '"
					+ secondRegex + "'");
	}
