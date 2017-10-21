	@TestFactory
	Iterator<DynamicTest> dynamicTestsFromIterator() {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		return Arrays.asList(
			dynamicTest("5th dynamic test", () -> assertTrue(true)),
			dynamicTest("6th dynamic test", () -> assertEquals(4, 2 * 2))
		).iterator();
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
