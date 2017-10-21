	@Test
	void composingMultipleFiltersIsAConjunctionOfFilters() {
		Filter<String> firstFilter = ClassNameFilter.includeClassNamePatterns(".*ring.*");
		Filter<String> secondFilter = ClassNameFilter.includeClassNamePatterns(".*Join.*");

		Filter<String> composed = Filter.composeFilters(firstFilter, secondFilter);

		assertFalse(composed.apply("java.lang.String").included());
		assertFalse(composed.toPredicate().test("java.lang.String"));
		assertTrue(composed.apply("java.util.StringJoiner").included());
		assertTrue(composed.toPredicate().test("java.util.StringJoiner"));
	}
