	@TestFactory
	Iterable<DynamicTest> dynamicTestsFromIterable() {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		return Arrays.asList(
			dynamicTest("3rd dynamic test", () -> assertTrue(true)),
			dynamicTest("4th dynamic test", () -> assertEquals(4, 2 * 2))
		);
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
