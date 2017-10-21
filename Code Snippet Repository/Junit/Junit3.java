	@TestFactory
	Collection<DynamicTest> dynamicTestsFromCollection() {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		return Arrays.asList(
			dynamicTest("1st dynamic test", () -> assertTrue(true)),
			dynamicTest("2nd dynamic test", () -> assertEquals(4, 2 * 2))
		);
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
