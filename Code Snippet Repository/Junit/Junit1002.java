	@Test
	void excludeClassNamePatternsWithMultiplePatterns() {
		String firstRegex = "^java\\.lang\\..*";
		String secondRegex = "^java\\.util\\..*";

		ClassNameFilter filter = ClassNameFilter.excludeClassNamePatterns(firstRegex, secondRegex);

		assertThat(filter).hasToString(
			"Excludes class names that match regular expression '" + firstRegex + "' OR '" + secondRegex + "'");

		assertTrue(filter.apply("java.lang.String").excluded());
		assertFalse(filter.toPredicate().test("java.lang.String"));
		assertThat(filter.apply("java.lang.String").getReason()).contains(
			"Class name [java.lang.String] matches excluded pattern: '" + firstRegex + "'");

		assertTrue(filter.apply("java.util.Collection").excluded());
		assertFalse(filter.toPredicate().test("java.util.Collection"));
		assertThat(filter.apply("java.util.Collection").getReason()).contains(
			"Class name [java.util.Collection] matches excluded pattern: '" + secondRegex + "'");

		assertFalse(filter.apply("java.time.Instant").excluded());
		assertTrue(filter.toPredicate().test("java.time.Instant"));
		assertThat(filter.apply("java.time.Instant").getReason()).contains(
			"Class name [java.time.Instant] does not match any excluded pattern: '" + firstRegex + "' OR '"
					+ secondRegex + "'");
	}
