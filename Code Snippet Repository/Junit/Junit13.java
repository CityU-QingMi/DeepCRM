	@TestFactory
	default Collection<DynamicTest> dynamicTestsFromCollection() {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		return Arrays.asList(
			dynamicTest("1st dynamic test in test interface", () -> assertTrue(true)),
			dynamicTest("2nd dynamic test in test interface", () -> assertEquals(4, 2 * 2))
		);
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
