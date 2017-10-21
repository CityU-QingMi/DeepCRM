	@Test
	void aFilterComposedOfMultipleFiltersHasReadableDescription() {
		Filter<Object> firstFilter = new FilterStub<>(o -> excluded("wrong"), () -> "1st");
		Filter<Object> secondFilter = new FilterStub<>(o -> included("right"), () -> "2nd");

		Filter<Object> composed = Filter.composeFilters(firstFilter, secondFilter);

		assertFalse(composed.apply(String.class).included());
		assertEquals("(1st) and (2nd)", composed.toString());
	}
