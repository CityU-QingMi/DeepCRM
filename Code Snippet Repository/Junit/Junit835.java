	@Test
	void evaluatesMultipleFilters() {
		Filter filter1 = mockFilter("foo", false);
		Filter filter2 = mockFilter("bar", true);

		OrFilter orFilter = new OrFilter(asList(filter1, filter2));

		assertEquals("foo OR bar", orFilter.describe());

		Description description = Description.createTestDescription(getClass(), "evaluatesMultipleFilters");
		assertTrue(orFilter.shouldRun(description));

		verify(filter1).shouldRun(same(description));
		verify(filter2).shouldRun(same(description));
	}
