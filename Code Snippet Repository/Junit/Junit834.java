	@Test
	void evaluatesSingleFilter() {
		Filter filter = mockFilter("foo", true);

		OrFilter orFilter = new OrFilter(singleton(filter));

		assertEquals("foo", orFilter.describe());

		Description description = Description.createTestDescription(getClass(), "evaluatesSingleFilter");
		assertTrue(orFilter.shouldRun(description));

		verify(filter).shouldRun(same(description));
	}
